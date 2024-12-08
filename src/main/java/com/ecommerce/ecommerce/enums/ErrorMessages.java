package com.ecommerce.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorMessages {
    CATEGORY_NOT_FOUND("Category not found"),
    USER_NOT_FOUND("User not found"),
    PRODUCT_NOT_FOUND("Product not found");
    private final String message;
}
