package com.ecommerce.ecommerce.trees;




import java.util.List;

import org.springframework.stereotype.Component;

import com.ecommerce.ecommerce.models.Category;


@Component
public class CategoryTreeNode {
    private CategoryNode root;

    // Method to build a tree from a list of categories
    public CategoryNode buildTree(List<Category> categories) {
        for (Category category : categories) {
            root = insertNode(root, category);
        }
        return root;
    }

    // Method to insert a new category into the tree
    public CategoryNode insertNode(CategoryNode node, Category category) {
        if (node == null) {
            return new CategoryNode(category);
        }

        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());

        // Insert into the smaller subtree to maintain balance
        if (leftHeight <= rightHeight) {
            node.setLeft(insertNode(node.getLeft(), category));
        } else {
            node.setRight(insertNode(node.getRight(), category));
        }

        return node;
    }

    // Method to get the height of a subtree
    private int getTreeHeight(CategoryNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getTreeHeight(node.getLeft()), getTreeHeight(node.getRight())) + 1;
    }

    // Check if the parent already has two children
    public boolean hasTwoChildren(Category parent) {
        return parent.getLeftPosition() != null && parent.getRightPosition() != null;
    }

    // Check if a specific position (left or right) is occupied
    public boolean isPositionOccupied(Category parent, boolean isLeftPosition) {
        if (isLeftPosition) {
            return parent.getLeftPosition() != null;
        } else {
            return parent.getRightPosition() != null;
        }
    }


    
}
