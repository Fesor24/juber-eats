package com.app.JuberEats.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemResponse {
    private Long productId;
    private String productName;
    private Long restaurantId;
    private String restaurantName;
    private Integer quantity;
}
