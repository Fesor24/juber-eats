package com.app.JuberEats.controller;

import com.app.JuberEats.primitives.Result;
import com.app.JuberEats.primitives.ResultT;
import com.app.JuberEats.request.cart.CreateShoppingCartRequest;
import com.app.JuberEats.response.cart.GetShoppingCartResponse;
import com.app.JuberEats.service.IShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api")
@Tag(name = "Shopping Cart", description = "Shopping cart endpoints")
public class ShoppingCartController {

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Operation(summary = "Create shopping cart",
            description = "Endpoint to create shopping cart")
    @PostMapping("/v1/public/cart")
    public ResponseEntity<ResultT<GetShoppingCartResponse>> createCart(
            @Valid
            @RequestBody CreateShoppingCartRequest request){

        GetShoppingCartResponse response = shoppingCartService.createOrUpdateCart(request);

        return new ResponseEntity<>(new ResultT<>(response), HttpStatus.OK);
    }

    @Operation(summary = "Get shopping cart",
            description = "Endpoint to get shopping cart")
    @GetMapping("/v1/public/cart/{cartId}")
    public ResponseEntity<ResultT<GetShoppingCartResponse>> getCart(@PathVariable String cartId){
        GetShoppingCartResponse cart = shoppingCartService.getCart(cartId);

        return new ResponseEntity<>(new ResultT<>(cart), HttpStatus.OK);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    content = {@Content(mediaType = "application/json")})
    })
    @Operation(summary = "Delete shopping cart",
        description = "Endpoint to delete shopping cart")
    @DeleteMapping("/v1/public/cart/{cartId}")
    public ResponseEntity<Result> deleteCart(@PathVariable String cartId){
        shoppingCartService.deleteCart(cartId);

        return new ResponseEntity<>(new Result(), HttpStatus.NO_CONTENT);
    }
}
