package com.app.JuberEats.request.restaurant;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRestaurantRequest {
    @NotEmpty
    private String name;
}
