package com.app.JuberEats.service;

import com.app.JuberEats.request.order.CreateOrderRequest;
import com.app.JuberEats.response.order.GetOrderResponse;

public interface IOrderService {
    GetOrderResponse create(CreateOrderRequest request);
    void cancelOrder(Long orderId);
    GetOrderResponse getOrder(Long orderId);
}
