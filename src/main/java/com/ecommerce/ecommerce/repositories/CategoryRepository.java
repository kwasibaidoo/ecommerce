package com.ecommerce.ecommerce.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Page<Category> findByDeletedAtIsNull(Pageable pageable);
    boolean existsByName(String name);
    Page<Category> findByNameContainingIgnoreCaseAndDeletedAtIsNull(String query, Pageable pageable);
    Optional<Category> findByIdAndDeletedAtIsNull(UUID id);
    List<Category> findByDeletedAtIsNull();
    
}
