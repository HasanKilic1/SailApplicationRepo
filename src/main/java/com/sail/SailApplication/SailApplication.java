package com.sail.SailApplication;

import com.sail.SailApplication.StudentEntity.Student;
import com.sail.SailApplication.StudentEntity.StudentService;
import com.sail.SailApplication.StudentRepository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SailApplication {
	@Autowired
	StudentRepository studentRepository;
	public static void main(String[] args) {
		var context = SpringApplication.run(SailApplication.class, args);
		StudentService studentService = context.getBean(StudentService.class);
	}
}
