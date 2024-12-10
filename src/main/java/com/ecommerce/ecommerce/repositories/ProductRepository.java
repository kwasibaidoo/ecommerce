package com.ecommerce.ecommerce.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByDeletedAtIsNull(Pageable pageable);
    boolean existsByName(String name);
    Page<Product> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String query, Pageable pageable);
    Optional<Product> findByIdAndDeletedAtIsNull(UUID id);

}
