package com.sail.SailApplication.AdvisorEntity;

import com.sail.SailApplication.RequestEntity.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvisorService {

    private final AdvisorRepository advisorRepository;
    private final RequestService requestService;
    @Autowired
    public AdvisorService(AdvisorRepository advisorRepository, RequestService requestService) {
        this.advisorRepository = advisorRepository;
        this.requestService = requestService;
    }

    public Advisor getAdvisorById(Long id){
        return advisorRepository.findAdvisorById(id);
    }

    public List<Advisor> getAllAdvisors(){
        return advisorRepository.findAll();
    }

    public void addNewAdvisor(Advisor advisor){
        advisorRepository.save(advisor);
    }

    public boolean updateAdvisorMail(Long id , String newMail){

        if(advisorRepository.findAdvisorById(id) == null) return false;

        Advisor toUpdate = advisorRepository.findAdvisorById(id);
        toUpdate.setEmail(newMail);
        advisorRepository.save(toUpdate);
        return true;
    }

    public boolean updateAdvisorPassword(Long id , String newPassword){

        if(advisorRepository.findAdvisorById(id) == null) return false;

        Advisor toUpdate = advisorRepository.findAdvisorById(id);
        toUpdate.setPassword(newPassword);
        advisorRepository.save(toUpdate);
        return true;
    }

    public boolean deleteAdvisorById(Long id) {
        if(advisorRepository.findAdvisorById(id) == null) return false;
        else {
            advisorRepository.deleteById(id);
            return true;
        }
    }

    public boolean acceptStudentRequest(Long requestId){
        boolean accepted = requestService.acceptRequest(requestId);
        return accepted;
    }
}
