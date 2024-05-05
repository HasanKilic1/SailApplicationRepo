package com.sail.SailApplication.AdvisorEntity;

import com.sail.SailApplication.RequestEntity.Request;
import com.sail.SailApplication.StudentEntity.Student;
import com.sail.SailApplication.helpers.EntityUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController()
public class AdvisorController {
    private final AdvisorService advisorService;

    @Autowired
    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    //SHOW ALL ADVISORS
    @GetMapping("/advisors")
    public List<Advisor> getAllAdvisors(){
        return advisorService.getAllAdvisors();
    }

    //GET ADVISOR BY GIVEN ID
    @GetMapping("/advisors/{advisor-id}")
    public Advisor getAdvisorById(@PathVariable("advisor-id") Long id){
        return advisorService.getAdvisorById(id);
    }

    //ADD NEW ADVISOR TO DATABASE
    @PostMapping("/advisors/post-advisor")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAdvisor(@RequestBody Advisor advisor){
        advisorService.addNewAdvisor(advisor);
    }

    //UPDATE THE MAIL OF ADVISOR
    @PutMapping("/put-advisor/update-mail")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> updateAdvisorMail(@RequestBody EntityUpdateRequest request) {
        boolean updated = advisorService.updateAdvisorMail(request.getId(), request.getNewMail());

        if (updated) {
            return ResponseEntity.ok("Student email updated successfully");
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no student that has given id");}
    }

    //DELETE ADVISOR BY GIVEN ID
    @DeleteMapping("/delete-advisor/{advisor-id}")
    public ResponseEntity<String> deleteAdvisor(@PathVariable("advisor-id") Long id){
        boolean deleted = advisorService.deleteAdvisorById(id);
        if(deleted)
        {
            return ResponseEntity.ok("Advisor deleted successfully");
        }
        else { return  ResponseEntity.status(HttpStatus.CONFLICT).body("There is no advisor that has given id");
        }
    }

    //GET ALL STUDENTS OF ADVISOR BY GIVEN ADVISOR ID
    @GetMapping("/advisors/{advisorId}")
    public List<Student> getStudentsOfAdvisor(@PathVariable Long advisorId) {
        return advisorService.getAdvisorById(advisorId).getStudents();
    }

    //GET ALL REQUESTS OF ADVISOR BY GIVEN ADVISOR ID
    @GetMapping("/advisors/{advisorId}/requests")
    public ResponseEntity<?> getRequestsOfAdvisor(@PathVariable Long advisorId) {
        try {
            Advisor advisor = advisorService.getAdvisorById(advisorId);
            List<Request> requests = advisor.getRequests();
            return ResponseEntity.ok(requests);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Advisor not found with ID: " + advisorId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while retrieving requests for advisor with ID: " + advisorId);
        }
    }

    //ACCEPT THE REQUEST BY GIVEN REQUEST ID
    @PostMapping("/accept-request/{requestId}")
    public ResponseEntity<String> acceptStudentRequest(@PathVariable Long requestId) {
        boolean accepted = advisorService.acceptStudentRequest(requestId);

        if (accepted) {
            return ResponseEntity.ok("Request accepted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to accept request, please try again later");
        }
    }
}
