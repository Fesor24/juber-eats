package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.request.restaurant.CreateRestaurantRequest;
import com.app.JuberEats.request.restaurant.SearchRestaurantRequestParams;
import com.app.JuberEats.response.PaginatedList;
import com.app.JuberEats.response.restaurant.GetRestaurantResponse;
import com.app.JuberEats.response.restaurant.SearchRestaurantResponse;
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
        description = "Search endpoint to return list of restaurants")
    @GetMapping("/v1/restaurants")
    public ResponseEntity<ResultT<PaginatedList<SearchRestaurantResponse>>> search(SearchRestaurantRequestParams request){
        PaginatedList<SearchRestaurantResponse> paginatedList = restaurantService.search(request);

        ResultT<PaginatedList<SearchRestaurantResponse>> response = new ResultT<>(paginatedList);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Returns restaurant by an identifier",
        description = "Endpoint to return a restaurant using an identifier")
    @GetMapping("/v1/public/restaurant/{restaurantId}")
    public ResponseEntity<ResultT<GetRestaurantResponse>> getById(
            @Parameter(description = "ID of restaurant to be fetched", required = true)
            @PathVariable @Min(1) Long restaurantId
    ){
        return new ResponseEntity<>(
                new ResultT<>(this.restaurantService.getById(restaurantId)),
                HttpStatus.OK);
    }

    @Operation(summary = "Endpoint to create a restaurant",
    description = "Endpoint to create a restaurant")
    @PostMapping("/admin/restaurant")
    public ResponseEntity create(
            @Valid
            @RequestBody CreateRestaurantRequest request){
        this.restaurantService.createRestaurant(request);

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
