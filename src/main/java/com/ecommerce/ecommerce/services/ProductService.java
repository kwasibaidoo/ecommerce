package com.ecommerce.ecommerce.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
        Category category = categoryRepository.findById(uuid).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);

    }

    public List<Product> getProducts() {
        return productRepository.findByDeletedAtIsNull();
    }

    public Product getProduct(String id) {
        UUID uuid = UUID.fromString(id);
        return productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));
    }

    public void updateProduct(String id, ProductDTO productDTO) {
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));
        Category category = categoryRepository.findById(UUID.fromString(productDTO.getCategory_id())).orElseThrow(() -> new CategoryNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND.getMessage()));

        product.setName(productDTO.getName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());

        productRepository.save(product);


    }

    public void deleteProduct(String id){
        UUID uuid = UUID.fromString(id);
        Product product = productRepository.findById(uuid).orElseThrow(() -> new ProductNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND.getMessage()));

        productRepository.delete(product);
    }
}
