package com.sail.SailApplication.StudentEntity;

import com.sail.SailApplication.StudentRepository.StudentRepository;
import com.sail.SailApplication.helpers.EntityUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    //StudentRestController is responsible for GET,POST,PUT,DELETE (CRUD) operations
    @Autowired
    private final StudentService studentService;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //  <Endpoints>
    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    @GetMapping("/students/{student-id}")
    @ResponseStatus(HttpStatus.OK)
    public Student getStudentById(@PathVariable("student-id") Long id){
        return studentService.getStudentById(id);
    }

    @PostMapping("/students/post-student")
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @PutMapping("/students/update-mail")
    public ResponseEntity<String> updateStudentMail(@RequestBody EntityUpdateRequest request) {
    boolean updated = studentService.updateStudentMail(request.getId(), request.getNewMail());

    if (updated) {
       return ResponseEntity.ok("Student email updated successfully");
    }
    else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no student that has given id");}
    }

    @PutMapping("/students/update-password")
    public ResponseEntity<String> updateStudentPassword(@RequestBody EntityUpdateRequest request) {
        boolean updated = studentService.updateStudentPassword(request.getId(), request.getNewPassword());

        if (updated) {
            return ResponseEntity.ok("Student password updated successfully");
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no student that has given id");}
    }

    @DeleteMapping("/students/delete-student/{student-id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("student-id") Long id){
        boolean deleted = studentService.deleteStudentById(id);
        if(deleted)
        {
            return ResponseEntity.ok("Student deleted successfully");
        }
        else { return  ResponseEntity.status(HttpStatus.CONFLICT).body("There is no student that has given id");
        }
    }

    @PostMapping("/students/send-request/{studentId}/{advisorId}/{message}")
    public ResponseEntity<String> sendRequestToAdvisor(@PathVariable("studentId") Long studentId,
                                                       @PathVariable("advisorId") Long advisorId,
                                                       @PathVariable("message") String message){
        try {
            boolean successfullyCreated = studentService.sendRequestToAdvisor(studentId, advisorId, message);
            if (successfullyCreated) {
                return ResponseEntity.ok().body("Request sent successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to send request, please try again later");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Invalid studentId or advisorId provided");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request");
        }
    }

}
