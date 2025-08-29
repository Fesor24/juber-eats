package com.app.JuberEats.service;

import com.app.JuberEats.entity.restaurants.Restaurant;
import com.app.JuberEats.request.restaurant.CreateRestaurantRequest;
import com.app.JuberEats.request.restaurant.SearchRestaurantRequestParams;
import com.app.JuberEats.response.PaginatedList;
import com.app.JuberEats.response.restaurant.GetRestaurantResponse;
import com.app.JuberEats.response.restaurant.SearchRestaurantResponse;

import java.util.List;

public interface IRestaurantService {
    PaginatedList<SearchRestaurantResponse> search(SearchRestaurantRequestParams request);
    void createRestaurant(CreateRestaurantRequest restaurant);
    void deleteRestaurant(Long restaurantId);
    GetRestaurantResponse getById(Long restaurantId);
}
