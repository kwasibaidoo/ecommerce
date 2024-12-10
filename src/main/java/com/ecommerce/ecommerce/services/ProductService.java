package com.ecommerce.ecommerce.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.data_transfer_objects.ProductDTO;
import com.ecommerce.ecommerce.enums.ErrorMessages;
import com.ecommerce.ecommerce.exceptions.CategoryNotFoundException;
import com.ecommerce.ecommerce.exceptions.ProductNotFoundException;
import com.ecommerce.ecommerce.models.Category;
import com.ecommerce.ecommerce.models.Product;
import com.ecommerce.ecommerce.repositories.CategoryRepository;
import com.ecommerce.ecommerce.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        UUID uuid = UUID.fromString(productDTO.getCategory_id());
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);

    }

    public Page<Product> getProducts(int page, int size, String direction, String sortBy) {
        Sort.Direction sortDirection = Sort.Direction.fromString(sortBy);
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        return productRepository.findByDeletedAtIsNull(pageable);
    }

    public Product getProduct(String id) {
        UUID uuid = UUID.fromString(id);
        return productRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));
    }

    public void updateProduct(String id, ProductDTO productDTO) {
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findByIdAndDeletedAtIsNull(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));
        Category category = categoryRepository.findByIdAndDeletedAtIsNull(UUID.fromString(productDTO.getCategory_id())).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);


    }

    public void deleteProduct(String id){
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));
        product.setDeletedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    public Page<Product> searchProduct(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingIgnoreCaseAndDeletedAtIsNull(query, pageable);
    }
}
