package com.app.JuberEats.controller;

import com.app.JuberEats.entity.Restaurant;
import com.app.JuberEats.service.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Tag(name = "Restaurant API", description = "Restaurant API endpoints")
@RestController()
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;

    @Operation(summary = "Returns list of restaurants",
        description = "Endpoint to return all list of restaurants")
    @GetMapping("/v1/restaurants")
    public ResponseEntity<List<Restaurant>> getAll(){
        return new ResponseEntity<List<Restaurant>>(this.restaurantService.getAll(),
        HttpStatus.OK);
    }

    @PostMapping("/admin/restaurant")
    public ResponseEntity create(Restaurant restaurant){
        this.restaurantService.createRestaurant(restaurant);

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/restaurant/{restaurantId}")
    public ResponseEntity delete(
            @Parameter(description = "ID of restaurant to be deleted", required = true)
            @PathVariable Long restaurantId){
        try{
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
