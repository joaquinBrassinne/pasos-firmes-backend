package com.nocountry.apiS16.service.implementations;

import com.nocountry.apiS16.dto.CategoryDTO;
import com.nocountry.apiS16.exceptions.ResourceNotFoundException;
import com.nocountry.apiS16.model.Category;
import com.nocountry.apiS16.repository.ICategoryRepository;
import com.nocountry.apiS16.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;
    @Autowired
    private IProductRepository iProductRepository;

    public Category createCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            throw new IllegalArgumentException("CategoryDTO cannot be null");
        }

        Category category = new Category();
        category.setName(categoryDTO.getName());

        return iCategoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        return iCategoryRepository.findAll();
    }

        public Category getCategoryById(Long id)throws ResourceNotFoundException {
            Category category = iCategoryRepository.findById(id).orElse(null);
            if (category == null) {
                throw new ResourceNotFoundException("Category not found with id: " + id);
            }
            return category;
        }

    public Category getCategoryByName(String name) throws ResourceNotFoundException {
        Category category = iCategoryRepository.findByName(name).orElse(null);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found with id: " + name);
        }
        return category;
    }
    public Category updateCategory(Long id, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category existingCategory = iCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

        existingCategory.setName(categoryDTO.getName());
        return iCategoryRepository.save(existingCategory);
    }

    public void deleteById(Long id) {
        iCategoryRepository.deleteById(id);
    }

    public Category findByName(String name) {
        return iCategoryRepository.findByName(name).orElse(null);
    }
}

