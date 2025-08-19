package com.app.JuberEats.controller;

import com.app.JuberEats.entity.Restaurant;
import com.app.JuberEats.service.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController()
@RequestMapping("/api")
public class RestaurantController {
    @Autowired
    private IRestaurantService restaurantService;

    @GetMapping("/public/restaurants")
    public ResponseEntity<List<Restaurant>> getAll(){
        return new ResponseEntity<List<Restaurant>>(this.restaurantService.getAll(),
        HttpStatus.OK);
    }

    @DeleteMapping("/admin/restaurant/{restaurantId}")
    public ResponseEntity delete(@PathVariable Long restaurantId){
        try{
            return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
