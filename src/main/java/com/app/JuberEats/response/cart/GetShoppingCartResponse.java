package com.app.JuberEats.response.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetShoppingCartResponse {
    private String id;
    private List<ShoppingCartItemResponse> items;
}
