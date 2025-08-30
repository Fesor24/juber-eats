package com.app.JuberEats.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetRestaurantResponse {
    private Long restaurantId;
    private String name;
}
