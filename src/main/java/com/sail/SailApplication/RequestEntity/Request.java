package com.sail.SailApplication.RequestEntity;

import com.sail.SailApplication.AdvisorEntity.Advisor;
import com.sail.SailApplication.StudentEntity.Student;
import jakarta.persistence.*;

@Entity
@Table
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    Student student;

    @ManyToOne
    Advisor advisor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public Request(Long id, Student student, Advisor advisor, String message) {
        this.id = id;
        this.student = student;
        this.advisor = advisor;
        this.message = message;
    }

    public Request() {
    }
}
