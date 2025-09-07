package com.app.JuberEats.request.cart;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateShoppingCartRequest {
    @NotEmpty()
    @NotNull()
    private String cartId;

    @Valid()
    private List<CreateShoppingCartItemRequest> items = new ArrayList<>();
}
