package com.example.productApplication.service;

import com.example.productApplication.model.Course;
import com.example.productApplication.model.Student;
import com.example.productApplication.model.StudentPage;
import com.example.productApplication.model.StudentSearchCriteria;
import com.example.productApplication.repository.StudentCriterialRepositoryImpl;
import com.example.productApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCriterialRepositoryImpl studentCriteriaRepositoryImpl;

    public Student create(Student student) {
        if (student.getCourses() != null) {
            for (Course course : student.getCourses()) {
                course.getStudents().add(student);
            }
        }
        return studentRepository.save(student);
    }

    public Iterable<Student> allStudents() {
        return studentRepository.findAll();
    }


    public Page<Student> getStudentsPaged(StudentPage studentPage, StudentSearchCriteria studentSearchCriteria) {
        return studentCriteriaRepositoryImpl.findAllWithFilters(studentPage, studentSearchCriteria);
    }
}