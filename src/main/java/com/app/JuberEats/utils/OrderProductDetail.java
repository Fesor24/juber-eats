package com.app.JuberEats.utils;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderProductDetail {
    private String productName;
    private BigDecimal price;
}
