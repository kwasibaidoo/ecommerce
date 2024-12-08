package com.ecommerce.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findByDeletedAtIsNull();
    boolean existsByName(String name);
    List<Category> findByNameIgnoreCaseAndDeletedAtIsNull(String query);
    Optional<Category> findByIdAndDeletedAtIsNull(UUID id);
    
}
