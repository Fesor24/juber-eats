package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.Result;
import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.request.order.CreateOrderRequest;
import com.app.JuberEats.response.order.GetOrderResponse;
import com.app.JuberEats.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "Order", description = "Order controller")
public class OrderController extends BaseController{

    @Autowired
    private IOrderService orderService;

    @Operation(summary = "Create order", description = "Endpoint to create order")
    @PostMapping("/v1/order")
    public ResponseEntity<ResultT<GetOrderResponse>> createOrder(@RequestBody CreateOrderRequest request){
        GetOrderResponse orderResponse = orderService.create(request);

        return OK(orderResponse);
    }

    @Operation(summary = "Cancel order endpoint", description = "Endpoint to cancel an order")
    @PutMapping("/v1/order")
    public ResponseEntity<Result> cancelOrder(@PathVariable Long orderId){
        orderService.cancelOrder(orderId);

        return OK();
    }

    @Operation(summary = "Get order endpoint", description = "Endpoint to get an order")
    @GetMapping("/v1/order")
    public ResponseEntity<ResultT<GetOrderResponse>> getOrder(@PathVariable Long orderId){
        GetOrderResponse orderResponse = orderService.getOrder(orderId);

        return OK(orderResponse);
    }
}
