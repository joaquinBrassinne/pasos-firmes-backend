package com.nocountry.apiS16.controller;

import com.nocountry.apiS16.dto.CategoryDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Category;
import com.nocountry.apiS16.service.implementations.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) throws ResourceNotFoundException  {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found with id: " + id);
        }
        return category;
    }

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategory();
    }

    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable String name) throws ResourceNotFoundException {
        return categoryService.getCategoryByName(name);
    }
    @PostMapping("/add")
    public Category createCategory (@RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        return categoryService.createCategory(categoryDTO);
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        return categoryService.updateCategory(id, categoryDTO);
    }
    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteCategoryByName(@PathVariable String name) {
        categoryService.findByName(name);
    }


}
