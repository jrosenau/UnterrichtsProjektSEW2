package com.example.productApplication.controller;

import com.example.productApplication.model.Student;
import com.example.productApplication.model.StudentPage;
import com.example.productApplication.model.StudentSearchCriteria;
import com.example.productApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @PostMapping("/students")
    public Student createStudent(@RequestBody Student student) {
        return studentService.create(student);
    }

    @GetMapping("/all")
    public Iterable<Student> getAllStudents() {
        return studentService.allStudents();
    }



    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getStudentPaged( StudentPage studentPage,  StudentSearchCriteria studentSearchCriteria) {
        return new ResponseEntity<>(studentService.getStudentsPaged(studentPage, studentSearchCriteria), HttpStatus.OK);
    }
}