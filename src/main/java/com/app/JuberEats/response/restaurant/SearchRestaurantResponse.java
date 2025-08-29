package com.app.JuberEats.response.restaurant;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRestaurantResponse {
    private Long restaurantId;
    private String name;
}
