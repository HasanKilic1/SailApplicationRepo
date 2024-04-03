package com.sail.SailApplication.StudentEntity;

import com.sail.SailApplication.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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

    // Environment Test
  /*   private Environment environment;
    @Autowired
    public  void setEnvironment(Environment environment){
        this.environment = environment;
    }

    public String getJavaVersion()
    {
        return environment.getProperty("java.version");
    }

    public String readCustomProperty(){ //for test
        return  environment.getProperty("sail.custom.property");
    }
  */
}
