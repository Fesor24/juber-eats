package com.app.JuberEats.repositories;

import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.entity.restaurants.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByIdIn(List<Long> ids);
}
