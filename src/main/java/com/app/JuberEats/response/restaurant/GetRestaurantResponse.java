package com.app.JuberEats.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetRestaurantResponse {
    private Long restaurantId;
    private String name;
}
