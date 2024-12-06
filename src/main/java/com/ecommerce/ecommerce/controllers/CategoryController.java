package com.ecommerce.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import jakarta.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDTO categoryDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }
        else{
            categoryService.addCategory(categoryDTO);
            return new ResponseEntity<>("Category created successfully", HttpStatus.CREATED);
        }
    }


    @GetMapping("/category")
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable("id") String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.status(200).body(category);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()) {
            categoryService.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Category deleted");
        }
        else{
            return ResponseEntity.status(404).body(null);
        }
    }


    @PutMapping("/category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable("id") String id, @Valid @RequestBody CategoryDTO categoryDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errors.append(error.getDefaultMessage()).append("\n"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.toString());
        }
        else {
            return new ResponseEntity<>("Category updated successfully", HttpStatus.OK);
        }
    }
}
