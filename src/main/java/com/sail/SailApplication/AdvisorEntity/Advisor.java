package com.sail.SailApplication.AdvisorEntity;

import com.sail.SailApplication.RequestEntity.Request;
import com.sail.SailApplication.StudentEntity.Student;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Advisors")
public class Advisor {
    @Id
    @SequenceGenerator(
            name = "advisor_sequence",
            sequenceName = "advisor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "advisor_sequence")
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "advisor" , cascade = CascadeType.ALL)
    private List<Student> students;



    @OneToMany(mappedBy = "Advisor")
    private List<Request> requests;

    public Advisor() {
    }

    public Advisor(String name, String surname, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Advisor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
