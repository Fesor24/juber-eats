package com.app.JuberEats.entity.cart;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash("ShoppingCart") // if we want to save it as a Hash...using redis template here
public class ShoppingCart {
    @Id
    private String id;
    private List<ShoppingCartItem> Items;
}
