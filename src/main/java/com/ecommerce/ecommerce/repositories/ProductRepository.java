package com.ecommerce.ecommerce.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByDeletedAtIsNull();
    boolean existsByName(String name);
    List<Product> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String query);
    Optional<Product> findByIdAndDeletedAtIsNull(UUID id);

}
