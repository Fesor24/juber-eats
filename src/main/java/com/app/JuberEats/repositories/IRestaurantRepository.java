package com.app.JuberEats.repositories;

import com.app.JuberEats.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
