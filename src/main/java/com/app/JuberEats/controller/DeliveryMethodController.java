package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.response.order.GetDeliveryMethodResponse;
import com.app.JuberEats.service.IDeliveryMethodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Delivery methods", description = "Delivery method controller")
public class DeliveryMethodController extends BaseController{

    @Autowired
    private IDeliveryMethodService deliveryMethodService;

    @Operation(summary = "Get delivery methods", description = "Endpoint to get delivery methods")
    @GetMapping("/v1/delivery-methods")
    public ResponseEntity<ResultT<List<GetDeliveryMethodResponse>>> getDeliveryMethods(){
        List<GetDeliveryMethodResponse> deliveryMethods =
                deliveryMethodService.getDeliveryMethods();

        return OK(deliveryMethods);
    }
}
