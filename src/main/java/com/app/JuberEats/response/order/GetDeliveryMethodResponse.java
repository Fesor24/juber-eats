package com.app.JuberEats.response.order;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GetDeliveryMethodResponse {
    public Long id;
    private String name;
    private String duration;
    private BigDecimal price;
}
