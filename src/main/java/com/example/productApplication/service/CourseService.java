package com.example.productApplication.service;


import com.example.productApplication.model.Course;
import com.example.productApplication.model.Student;
import com.example.productApplication.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseRepository create(Course course) {
        if (course.getStudents() != null) {
            for (Student student : course.getStudents()) {
                student.getCourses().add(course);
            }
        }
        return (CourseRepository) courseRepository.save(course);
    }

    public Iterable<Course> allCourses() {
        return courseRepository.findAll();
    }


}

