package com.app.JuberEats.service;

import com.app.JuberEats.entity.Restaurant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

@Service()
public class RestaurantService implements IRestaurantService {

    @Override
    public List<Restaurant> getAll() {
        return List.of();
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {

    }

    @Override
    public void deleteRestaurant(Long restaurantId) {

        Optional<Restaurant> restaurant = Optional.empty();

        if (restaurant.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Restautant not found");
        }
    }
}
