package com.app.JuberEats.entity.products;

import com.app.JuberEats.entity.restaurants.Restaurant;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long productId;
    @NotEmpty
    private String name;
    private String description;
    private ProductType type;
    @Column(precision = 19, scale = 2)
    private BigDecimal price;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
}
