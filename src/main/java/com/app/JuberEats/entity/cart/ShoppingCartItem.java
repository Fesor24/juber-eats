package com.app.JuberEats.entity.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartItem {
    private Integer productId;
    private String productName;
    private Integer restaurantId;
    private String restaurantName;
    private Integer quantity;
}
