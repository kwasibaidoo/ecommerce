package com.ecommerce.ecommerce.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = true)
    private Category parent;

    @ManyToOne
    @JoinColumn(name = "left_child_id", nullable = true)
    private Category leftPosition;

    @ManyToOne
    @JoinColumn(name = "right_child_id", nullable = true)
    private Category rightPosition;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updated_at;

    @Column(nullable = true)
    private LocalDateTime deletedAt;
}
