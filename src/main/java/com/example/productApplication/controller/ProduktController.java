package com.example.productApplication.controller;

import com.example.productApplication.model.Product;
import com.example.productApplication.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/product")
public class ProduktController {

    private final ProductService productService;

    public ProduktController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/products")
    public Iterable<Product> get() {
        return productService.getProducts();
    }

    @GetMapping("/products/{id}")
    public Product get(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        if (product == null) {
            return null;
        }
        return product;
    }

    @PostMapping("/products")
    public Product create(@RequestBody Product product) {
        return productService.putProduct(product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Long id) {
        productService.removeProduct(id);
    }
}