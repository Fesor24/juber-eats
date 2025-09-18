package com.app.JuberEats.response.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetDeliveryMethodResponse {
    private String name;
    private String duration;
    private BigDecimal price;
}
