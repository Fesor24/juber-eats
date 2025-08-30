package com.app.JuberEats.entity.restaurants;

import com.app.JuberEats.config.SchemaConstants;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restaurant_address")
public class RestaurantAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private String street;
    @Column
    private String city;
    @Column
    private String postcode;
}
