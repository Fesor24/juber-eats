package com.app.JuberEats.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateShoppingCartItemRequest {
    private Long productId;
    private Long restaurantId;
    private Integer quantity;
}
