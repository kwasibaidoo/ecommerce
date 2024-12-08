package com.ecommerce.ecommerce.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.data_transfer_objects.CategoryDTO;
import com.ecommerce.ecommerce.enums.ErrorMessages;
import com.ecommerce.ecommerce.exceptions.CategoryNotFoundException;
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
            Category parentCategory = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getParent_id()).orElse(null);
            category.setParent(parentCategory);
        }

        if (categoryDTO.getLeft_id() != null) {
            Category leftChild = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getLeft_id()).orElse(null);
            category.setLeftPosition(leftChild);
        }

        if (categoryDTO.getRight_id() != null) {
            Category rightChild = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getRight_id()).orElse(null);
            category.setRightPosition(rightChild);
        }

        categoryRepository.save(category);
        
    }

    public List<Category> getCategories() {
        return categoryRepository.findByDeletedAtIsNull();
    }

    public Category getCategoryById(String id) {
        UUID uuid = UUID.fromString(id);
        return categoryRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));
    }

    public void deleteCategory(String id) {
        UUID uuid = UUID.fromString(id);
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));
        category.setDeletedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }

    public void updateCategory(String id, CategoryDTO categoryDTO) {
        UUID uuid = UUID.fromString(id);
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParent_id() != null) {
            Category parentCategory = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getParent_id()).orElse(null);
            category.setParent(parentCategory);
        }

        if (categoryDTO.getLeft_id() != null) {
            Category leftChild = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getLeft_id()).orElse(null);
            category.setLeftPosition(leftChild);
        }

        if (categoryDTO.getRight_id() != null) {
            Category rightChild = categoryRepository.findByIdAndDeletedAtIsNull(categoryDTO.getRight_id()).orElse(null);
            category.setRightPosition(rightChild);
        }

        categoryRepository.save(category);
    }


    public List<Category> searchCategory(String query) {
        return categoryRepository.findByNameIgnoreCaseAndDeletedAtIsNull(query);
    }
}
