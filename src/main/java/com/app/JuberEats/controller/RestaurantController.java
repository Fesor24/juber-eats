package com.app.JuberEats.controller;

import com.app.JuberEats.entity.Restaurant;
import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.service.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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

    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResultT.class))})
    })
    @Operation(summary = "Returns list of restaurants",
        description = "Endpoint to return all list of restaurants")
    @GetMapping("/v1/restaurants")
    public ResponseEntity<ResultT<List<Restaurant>>> getAll(){
        return new ResponseEntity<ResultT<List<Restaurant>>>(
                new ResultT<List<Restaurant>>(this.restaurantService.getAll()), HttpStatus.OK);
    }

    @GetMapping("/v1/public/restaurant/{restaurantId}")
    public ResponseEntity getById(
            @Parameter(description = "ID of restaurant to be fetched", required = true)
            @PathVariable @Min(1) Long restaurantId
    ){
        return new ResponseEntity<>(this.restaurantService.getById(restaurantId),
                HttpStatus.OK);
    }

    @PostMapping("/admin/restaurant")
    public ResponseEntity create(
            @Valid
            @RequestBody Restaurant restaurant){
        this.restaurantService.createRestaurant(restaurant);

        return new ResponseEntity<>("", HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "404",
                    content = {@Content(mediaType = "application/json")},
                    description = "Restaurant not found")
    })
    @DeleteMapping("/admin/restaurant/{restaurantId}")
    public ResponseEntity delete(
            @Parameter(description = "ID of restaurant to be deleted", required = true)
            @PathVariable @Min(1) Long restaurantId){

        this.restaurantService.deleteRestaurant(restaurantId);

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }
}
