package com.example.productApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @NonNull
    @Column (name = "name", nullable = false)
    private String name;


    @OneToMany(mappedBy="category",
               orphanRemoval = true,
            cascade ={CascadeType.REMOVE, CascadeType.PERSIST},
            fetch = FetchType.LAZY)
    private List<Product> products;





}
