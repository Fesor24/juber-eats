package com.app.JuberEats.service;

import com.app.JuberEats.entity.restaurants.Restaurant;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.repositories.IRestaurantRepository;
import com.app.JuberEats.request.restaurant.CreateRestaurantRequest;
import com.app.JuberEats.request.restaurant.SearchRestaurantRequestParams;
import com.app.JuberEats.response.PaginatedList;
import com.app.JuberEats.response.restaurant.GetRestaurantResponse;
import com.app.JuberEats.response.restaurant.SearchRestaurantResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service()
public class RestaurantService implements IRestaurantService {

    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginatedList<SearchRestaurantResponse> search(SearchRestaurantRequestParams request) {

        Sort sort = Sort.by("name").ascending();

        Pageable pageInfo = PageRequest.of(request.getPageNumber(), request.getPageSize(), sort);

        Page<Restaurant> restaurantPage = this.restaurantRepository.findAll(pageInfo);

        List<SearchRestaurantResponse> restaurants = restaurantPage.getContent()
                .stream()
                .map((res) -> modelMapper.map(res, SearchRestaurantResponse.class))
                .toList();

        PaginatedList<SearchRestaurantResponse> res = new PaginatedList<>();
        res.setItems(restaurants);
        res.setPageNumber(request.getPageNumber());
        res.setPageSize(request.getPageSize());
        res.setTotalItems(restaurantPage.getTotalElements());

        return res;
    }

    @Override
    public void createRestaurant(CreateRestaurantRequest request) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
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
    public GetRestaurantResponse getById(Long restaurantId) {
        Optional<Restaurant> restaurantOpt = this.restaurantRepository
                .findById(restaurantId);

        return restaurantOpt
                .map((res) -> modelMapper.map(res, GetRestaurantResponse.class))
                .orElseThrow(() ->
                        new NotFoundException("Restaurant with ID:" + restaurantId + " not found"));
    }
}
