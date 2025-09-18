package com.app.JuberEats.service;

import com.app.JuberEats.response.order.GetDeliveryMethodResponse;

import java.util.List;

public interface IDeliveryMethodService {
    List<GetDeliveryMethodResponse> getDeliveryMethods();
}
