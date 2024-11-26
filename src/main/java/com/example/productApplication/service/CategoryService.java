package com.example.productApplication.service;

import com.example.productApplication.model.Category;
import com.example.productApplication.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category putCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category category) {
        Category categoryToUpdate = categoryRepository.findById(id).orElse(null);
        if (categoryToUpdate == null) {
            categoryToUpdate.setName(category.getName());
            return categoryRepository.save(categoryToUpdate);
        }
        return null;
    }

    public void removeCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
