package com.app.JuberEats.service;

import com.app.JuberEats.request.cart.CreateShoppingCartRequest;
import com.app.JuberEats.response.cart.GetShoppingCartResponse;

public interface IShoppingCartService {
    void deleteCart(String cartId);
    GetShoppingCartResponse getCart(String cartId);
    GetShoppingCartResponse createOrUpdateCart(CreateShoppingCartRequest cart);

}
