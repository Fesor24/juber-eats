package com.app.JuberEats.request.product;

import com.app.JuberEats.entity.products.ProductType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class CreateProductRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private Long restaurantId;
    @NotEmpty
    private ProductType productType;
    @NotNull
    @Min(1)
    private BigDecimal price;
}
