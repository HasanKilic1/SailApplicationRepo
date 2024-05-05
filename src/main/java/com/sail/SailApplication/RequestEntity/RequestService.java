package com.sail.SailApplication.RequestEntity;

import  com.sail.SailApplication.AdvisorEntity.Advisor;
import com.sail.SailApplication.StudentEntity.Student;
import com.sail.SailApplication.AdvisorEntity.AdvisorRepository;
import com.sail.SailApplication.StudentRepository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private StudentRepository studentRepository;


    public boolean createRequest(Long advisorId, Long studentId, String message) {
        Optional<Advisor> advisor = advisorRepository.findById(advisorId);

        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty() || student.get().getAdvisor() != null || advisor.isEmpty()){
            return false;
        }

        Request request = new Request();
        request.setAdvisor(advisor.get());
        request.setStudent(student.get());
        request.setMessage(message);

        advisor.get().getRequests().add(request);
        return true;
    }

    @Transactional
    public boolean acceptRequest(Long requestId){
        Optional<Request> request = requestRepository.findById(requestId);
        if(request.isEmpty()) return  false;

        Student senderStudent = request.get().student;
        Advisor accepterAdvisor = request.get().advisor;
        accepterAdvisor.getStudents().add(senderStudent);
        senderStudent.setAdvisor(accepterAdvisor);

        requestRepository.delete(request.get()); // Delete the request when it is accepted.
        return true;
    }
}
