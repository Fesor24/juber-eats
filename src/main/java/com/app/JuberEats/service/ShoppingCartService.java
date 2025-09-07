package com.app.JuberEats.service;

import com.app.JuberEats.entity.cart.ShoppingCart;
import com.app.JuberEats.entity.cart.ShoppingCartItem;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.request.cart.CreateShoppingCartRequest;
import com.app.JuberEats.response.cart.GetShoppingCartResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void deleteCart(String cartId) {
        cacheService.deleteItem(cartId);
    }

    @Override
    public GetShoppingCartResponse getCart(String cartId) {
        Optional<ShoppingCart> shoppingCart = cacheService.getItem(cartId, ShoppingCart.class);

        return shoppingCart
                .map(sc -> mapper.map(sc, GetShoppingCartResponse.class))
                .orElseThrow(() -> new NotFoundException("Cart with ID:" + cartId + " not found"));

    }

    @Override
    public GetShoppingCartResponse createOrUpdateCart(CreateShoppingCartRequest cart) {
        // todo: logic before saving basket
        // demo data for now...
        ShoppingCart shoppingCart = new ShoppingCart("cart:123456",
                List.of(
                        new ShoppingCartItem(1, "Milo", 3, "African store", 6),
                        new ShoppingCartItem(2, "Milk", 4, "Plaza store", 3)
                ));

        cacheService.save(shoppingCart.getId(), shoppingCart);

        return getCart(shoppingCart.getId());
    }
}
