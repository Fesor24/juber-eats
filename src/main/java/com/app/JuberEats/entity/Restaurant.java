package com.app.JuberEats.entity;

import jakarta.persistence.*;

@Entity(name = "restaurants")
public class Restaurant {

    public Restaurant(){

    }

    @Id
    @Column(name = "restaurant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;

    @Column(name = "name")
    private String name;
}
