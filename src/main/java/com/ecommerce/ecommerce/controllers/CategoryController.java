package com.ecommerce.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
}
