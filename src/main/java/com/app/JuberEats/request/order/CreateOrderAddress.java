package com.app.JuberEats.request.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderAddress {
    private String street;
    private String postCode;
    private String city;
}
