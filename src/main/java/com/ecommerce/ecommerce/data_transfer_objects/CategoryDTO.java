package com.ecommerce.ecommerce.data_transfer_objects;

import java.util.UUID;

import com.ecommerce.ecommerce.utils.UniqueCategory;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    @NotBlank
    @UniqueCategory(message = "Category name already taken")
    private String name;

    
    private UUID parent_id;

    
    private UUID left_id;

    
    private UUID right_id;
}
