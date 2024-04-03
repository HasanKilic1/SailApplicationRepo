package com.sail.SailApplication.AdvisorEntity;

import com.sail.SailApplication.helpers.EntityUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class AdvisorController {
    private final AdvisorService advisorService;

    @Autowired
    public AdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @GetMapping("/advisors")
    public List<Advisor> getAllAdvisors(){
        return advisorService.getAllAdvisors();
    }

    @GetMapping("/advisors/{advisor-id}")
    public Advisor getAdvisorById(@PathVariable("advisor-id") Long id){
        return advisorService.getAdvisorById(id);
    }

    @PostMapping("/advisors/post-advisor")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewAdvisor(@RequestBody Advisor advisor){
        advisorService.addNewAdvisor(advisor);
    }

    @PutMapping("/put-advisor/update-mail")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> updateAdvisorMail(@RequestBody EntityUpdateRequest request) {
        boolean updated = advisorService.updateAdvisorMail(request.getId(), request.getNewMail());

        if (updated) {
            return ResponseEntity.ok("Student email updated successfully");
        }
        else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no student that has given id");}
    }

    @DeleteMapping("/delete-advisor/{advisor-id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("advisor-id") Long id){
        boolean deleted = advisorService.deleteAdvisorById(id);
        if(deleted)
        {
            return ResponseEntity.ok("Advisor deleted successfully");
        }
        else { return  ResponseEntity.status(HttpStatus.CONFLICT).body("There is no advisor that has given id");
        }
    }


}
