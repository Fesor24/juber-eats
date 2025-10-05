package com.app.JuberEats.entity.orders;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter()
@Entity(name = "delivery_methods")
public class DeliveryMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String duration;

    @Column()
    private BigDecimal price;
}
