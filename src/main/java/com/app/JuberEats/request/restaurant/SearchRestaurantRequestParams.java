package com.app.JuberEats.request.restaurant;

import com.app.JuberEats.request.SearchRequestParams;
import lombok.Data;

@Data
public class SearchRestaurantRequestParams extends SearchRequestParams {
    private String name;
}
