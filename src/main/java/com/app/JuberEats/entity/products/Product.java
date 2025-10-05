package com.app.JuberEats.entity.products;

import com.app.JuberEats.config.SchemaConstants;
import com.app.JuberEats.entity.restaurants.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    private ProductType type;

    @Column(precision = 19, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
