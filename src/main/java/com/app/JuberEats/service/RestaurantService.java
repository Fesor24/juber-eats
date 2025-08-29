package com.app.JuberEats.service;

import com.app.JuberEats.entity.Restaurant;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service()
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> getAll() {
        return this.restaurantRepository.findAll();
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        this.restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Optional<Restaurant> restaurantOpt = this.restaurantRepository
                .findById(restaurantId);

        Restaurant restaurant = restaurantOpt
                .orElseThrow(() ->
                        new NotFoundException("Restaurant with ID: " +  restaurantId + " not found"));

       this.restaurantRepository.delete(restaurant);
    }

    @Override
    public Restaurant getById(Long restaurantId) {
        Optional<Restaurant> restaurantOpt = this.restaurantRepository
                .findById(restaurantId);

        return restaurantOpt
                .orElseThrow(() ->
                        new NotFoundException("Restaurant with ID:" + restaurantId + " not found"));
    }
}
