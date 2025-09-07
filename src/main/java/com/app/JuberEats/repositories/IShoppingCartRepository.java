package com.app.JuberEats.repositories;

import com.app.JuberEats.entity.cart.ShoppingCart;
import org.springframework.data.repository.CrudRepository;

public interface IShoppingCartRepository extends CrudRepository<ShoppingCart, String> {
}
