package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.data_transfer_objects.CategoryDTO;
import com.ecommerce.ecommerce.models.Category;
import com.ecommerce.ecommerce.repositories.CategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void addCategory(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParent_id() != null) {
            Category parentCategory = categoryRepository.findById(categoryDTO.getParent_id()).orElse(null);
            category.setParent(parentCategory);
        }

        if (categoryDTO.getLeft_id() != null) {
            Category leftChild = categoryRepository.findById(categoryDTO.getLeft_id()).orElse(null);
            category.setLeftPosition(leftChild);
        }

        if (categoryDTO.getRight_id() != null) {
            Category rightChild = categoryRepository.findById(categoryDTO.getRight_id()).orElse(null);
            category.setRightPosition(rightChild);
        }

        categoryRepository.save(category);
        
    }

    public List<Category> getCategories() {
        return categoryRepository.findByDeletedAtIsNull();
    }

    public Optional<Category> getCategoryById(String id) {
        UUID uuid = UUID.fromString(id);
        return categoryRepository.findById(uuid);
    }

    public void deleteCategory(String id) {
        UUID uuid = UUID.fromString(id);
        categoryRepository.deleteById(uuid);
    }
}
