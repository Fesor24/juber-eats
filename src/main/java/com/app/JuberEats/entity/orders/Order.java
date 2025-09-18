package com.app.JuberEats.entity.orders;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(
        name = "orders",
        indexes = {
                @Index(name = "idx_order_user_email", columnList = "user_email")
        }
)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "sub_total")
    private BigDecimal subTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_method_id")
    private DeliveryMethod deliveryMethod;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private OrderAddress address;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems;
}
