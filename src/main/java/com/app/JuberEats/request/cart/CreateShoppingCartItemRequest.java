package com.app.JuberEats.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShoppingCartItemRequest {
    private Integer productId;
    private Integer restaurantId;
    private Integer quantity;
}
