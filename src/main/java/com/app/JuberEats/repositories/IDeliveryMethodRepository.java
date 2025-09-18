package com.app.JuberEats.repositories;

import com.app.JuberEats.entity.orders.DeliveryMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryMethodRepository extends JpaRepository<DeliveryMethod, Long> {
}
