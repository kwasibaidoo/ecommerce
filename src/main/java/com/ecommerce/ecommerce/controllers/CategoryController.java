package com.ecommerce.ecommerce.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.data_transfer_objects.CategoryDTO;
import com.ecommerce.ecommerce.models.Category;
import com.ecommerce.ecommerce.services.CategoryService;
import com.ecommerce.ecommerce.utils.ValidationResponse;

import jakarta.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            ValidationResponse validationResponse = new ValidationResponse(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }
        else{
            categoryService.addCategory(categoryDTO);
            return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
        }
    }


    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") String id) {
        Category category = categoryService.getCategoryById(id);
        return ResponseEntity.status(200).body(category);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted");
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();
        if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            ValidationResponse validationResponse = new ValidationResponse(errors);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);
        }
        else {
            categoryService.updateCategory(id, categoryDTO);
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        }
    }
}
