package com.app.JuberEats.entity.restaurants;

import com.app.JuberEats.config.SchemaConstants;
import com.app.JuberEats.entity.products.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "restaurants")
public class Restaurant {

    public Restaurant(Long id, String name, RestaurantAddress address){
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_address_id") // the fk
    private RestaurantAddress address;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
}

// for bidirectional, mappedby can be specified in the non owning entity....