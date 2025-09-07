package com.app.JuberEats.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemResponse {
    private Integer productId;
    private String productName;
    private Integer restaurantId;
    private String restaurantName;
    private Integer quantity;
}
