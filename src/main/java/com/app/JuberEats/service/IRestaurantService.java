package com.app.JuberEats.service;

import com.app.JuberEats.entity.Restaurant;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IRestaurantService {
    List<Restaurant> getAll();
    void createRestaurant(Restaurant restaurant);
    void deleteRestaurant(Long restaurantId);
    Restaurant getById(Long restaurantId);
}
