package com.app.JuberEats.request.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrderRequest {
    private String cartId;
    private Long deliveryMethodId;
    private CreateOrderAddress address;
}
