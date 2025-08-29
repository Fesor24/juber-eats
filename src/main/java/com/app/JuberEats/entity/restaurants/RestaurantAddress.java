package com.app.JuberEats.entity.restaurants;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restaurant_address")
public class RestaurantAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_address_id")
    private Long restaurantAddressId;
    @Column
    private String description;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String postcode;
}
