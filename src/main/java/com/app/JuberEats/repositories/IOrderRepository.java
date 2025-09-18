package com.app.JuberEats.repositories;

import com.app.JuberEats.entity.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<Order, Long> {
}
