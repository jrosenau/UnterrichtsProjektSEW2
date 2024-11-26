package com.example.productApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "course_name")
    private String course_name;

    @Column(name = "abbreviation")
    private String abbreviation;

    @Column(name = "cost")
    private Double cost;

    @JsonIgnore
    @ManyToMany(mappedBy = "courses", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Student> students = new ArrayList<>();

    public Course() {
    }

    public Course(Integer id, String course_name, String abbreviation, Double cost) {
        this.id = id;
        this.course_name = course_name;
        this.abbreviation = abbreviation;
        this.cost = cost;
    }
}