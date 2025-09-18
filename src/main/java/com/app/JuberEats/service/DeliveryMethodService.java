package com.app.JuberEats.service;

import com.app.JuberEats.entity.orders.DeliveryMethod;
import com.app.JuberEats.repositories.IDeliveryMethodRepository;
import com.app.JuberEats.response.order.GetDeliveryMethodResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryMethodService implements IDeliveryMethodService{

    @Autowired
    private IDeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<GetDeliveryMethodResponse> getDeliveryMethods() {
        List<DeliveryMethod> deliveryMethods =  deliveryMethodRepository.findAll();

        return deliveryMethods.stream()
                .map((dm) -> mapper.map(dm, GetDeliveryMethodResponse.class))
                .toList();
    }
}
