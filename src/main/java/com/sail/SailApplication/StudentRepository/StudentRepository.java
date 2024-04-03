package com.sail.SailApplication.StudentRepository;

import com.sail.SailApplication.StudentEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student , Long> {
    Student findStudentById(Long id);
    List<Student> findStudentByEmail(String name);
    List<Student> findStudentByName(String name);
}
