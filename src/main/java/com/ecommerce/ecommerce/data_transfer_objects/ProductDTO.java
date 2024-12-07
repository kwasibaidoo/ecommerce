package com.ecommerce.ecommerce.data_transfer_objects;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String category_id;

    @NotNull
    @Min(0)
    private int quantity;

    @NotNull
    @Min(0)
    private Double price;
}
