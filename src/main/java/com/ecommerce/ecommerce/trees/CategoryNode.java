package com.ecommerce.ecommerce.trees;

import com.ecommerce.ecommerce.models.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNode {
    private Category category;
    private CategoryNode left;
    private CategoryNode right;

    public CategoryNode(Category category) {
        this.category = category;
    }
}
