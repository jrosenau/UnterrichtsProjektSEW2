package com.example.productApplication.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "price", nullable = false)
    double price;

    @Column(name = "name", nullable = false)
    String name;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
                cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JoinColumn(name = "category_id")
        private Category category;





}
