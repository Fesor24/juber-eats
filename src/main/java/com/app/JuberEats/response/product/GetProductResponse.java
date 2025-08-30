package com.app.JuberEats.response.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetProductResponse {
    Long productId;
    String name;
    BigDecimal price;
    String restaurant;
}
