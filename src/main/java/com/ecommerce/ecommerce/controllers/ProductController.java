package com.ecommerce.ecommerce.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.data_transfer_objects.ProductDTO;
import com.ecommerce.ecommerce.models.Product;
import com.ecommerce.ecommerce.services.ProductService;
import com.ecommerce.ecommerce.utils.ValidationResponse;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
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
            productService.addProduct(productDTO);
            return new ResponseEntity<>("Product added successfully", HttpStatus.OK);
        }
    }

    @GetMapping("/product")
    public ResponseEntity<Page<Product>> getProducts(
        @RequestParam(defaultValue = "0", required = false) int page, 
        @RequestParam(defaultValue = "10", required = false) int size,
        @RequestParam(defaultValue = "asc", required = false) String direction,
        @RequestParam(defaultValue = "name", required = false) String sortBy) {
        Page<Product> products = productService.getProducts(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") String id) {
        Product product = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") String id,@RequestBody @Valid ProductDTO productDTO, BindingResult bindingResult) {
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
            productService.updateProduct(id, productDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Product Updated Successfully");
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") String id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
    }

    @GetMapping("/product/search")
    public ResponseEntity<Page<Product>> searchProduct(@RequestParam String query, @RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "10", required = false) int size) {
        Page<Product> searchResults = productService.searchProduct(query, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(searchResults);
    }
}
