package com.example.productApplication.controller;

import com.example.productApplication.model.Category;
import com.example.productApplication.model.Product;
import com.example.productApplication.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/categories")
    public List<Category> get() {
        return categoryService.getCategories();
    }

    @GetMapping("/categories/{id}")
    public Category get(@PathVariable Long id) {
        Category category = categoryService.getCategory(id);
        if (category == null) {
            return null;
        }
        return category;
    }


    @PostMapping("/categories")
    public Category create(@RequestBody Category category) {
        if (category.getProducts() != null){
            for (Product product : category.getProducts()){
                product.setCategory(category);
            }
        }
        return categoryService.putCategory(category);
    }


    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.removeCategory(id);
    }


}
