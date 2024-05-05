package com.sail.SailApplication.StudentEntity;

import com.sail.SailApplication.RequestEntity.RequestService;
import com.sail.SailApplication.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RequestService requestService;
    @Autowired
    public StudentService(StudentRepository studentRepository, RequestService requestService) {
        this.studentRepository = studentRepository;
        this.requestService = requestService;
    }
    public Student getStudentById(Long id){
        return  studentRepository.findStudentById(id);
    }
    public void addNewStudent(Student student){
        studentRepository.save(student);
    }
    public boolean updateStudentMail(Long id , String newMail){

        if(studentRepository.findStudentById(id) == null) return false;

        Student toUpdate = studentRepository.findStudentById(id);
        toUpdate.setEmail(newMail);
        studentRepository.save(toUpdate);
        return true;
    }
    public boolean updateStudentPassword(Long id , String newPassword){
        if(studentRepository.findStudentById(id) == null) return false;

        Student toUpdate = studentRepository.findStudentById(id);
        toUpdate.setPassword(newPassword);
        studentRepository.save(toUpdate);
        return true;
    }

    public boolean deleteStudentById(Long id){
        Student toDelete = studentRepository.findStudentById(id);
        if(toDelete == null) return false;
        else{
            studentRepository.deleteById(id);
            return true;
        }
    }

    public boolean sendRequestToAdvisor(Long studentId , Long advisorId , String message){
        boolean created = requestService.createRequest(advisorId , studentId , message);
        return created;
    }
}
