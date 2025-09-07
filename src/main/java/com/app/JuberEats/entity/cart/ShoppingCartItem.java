package com.app.JuberEats.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {
    private Long productId;
    private String productName;
    private Long restaurantId;
    private String restaurantName;
    private Integer quantity;
}
