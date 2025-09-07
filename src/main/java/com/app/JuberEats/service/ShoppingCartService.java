package com.app.JuberEats.service;

import com.app.JuberEats.entity.cart.ShoppingCart;
import com.app.JuberEats.entity.cart.ShoppingCartItem;
import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.entity.restaurants.Restaurant;
import com.app.JuberEats.exceptions.BadRequestException;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.repositories.IProductRepository;
import com.app.JuberEats.repositories.IRestaurantRepository;
import com.app.JuberEats.request.cart.CreateShoppingCartItemRequest;
import com.app.JuberEats.request.cart.CreateShoppingCartRequest;
import com.app.JuberEats.response.cart.GetShoppingCartResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService implements IShoppingCartService {

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Override
    public void deleteCart(String cartId) {
        cacheService.deleteItem(cartId);
    }

    @Override
    public GetShoppingCartResponse getCart(String cartId) {
        Optional<ShoppingCart> shoppingCart = cacheService.getItem(cartId, ShoppingCart.class);

        return shoppingCart
                .map(sc -> mapper.map(sc, GetShoppingCartResponse.class))
                .orElseThrow(() -> new NotFoundException("Cart with ID:" + cartId + " not found"));

    }

    @Override
    public GetShoppingCartResponse createOrUpdateCart(CreateShoppingCartRequest request) {

        if(request.getItems().isEmpty()){
            throw new BadRequestException("Add at least one item to the cart");
        }

        List<Long> productIds = request.getItems().stream()
                .map(prd -> prd.getProductId())
                .toList();

        List<Product> products = productRepository.findByIdIn(productIds);

        List<Long> restaurantIds = request.getItems().stream()
                .map(res -> res.getRestaurantId())
                .toList();

        List<Restaurant> restaurants = restaurantRepository.findByIdIn(restaurantIds);

        ShoppingCart cart = new ShoppingCart(request.getCartId(), new ArrayList<>());

        for(CreateShoppingCartItemRequest item : request.getItems()){

            if(item.getQuantity().equals(0)){
                throw new BadRequestException("Quantity can not be 0");
            }



            Optional<Product> product = products.stream()
                    .filter(prd -> prd.getId().equals(item.getProductId()))
                    .findFirst();

            if(product.isEmpty()){
                throw new NotFoundException("Product with Id: " + item.getProductId() + " not found");
            }

            if(product.get().getRestaurant().getId() != item.getRestaurantId()){
                throw new NotFoundException("Product not found in restaurant products");
            }

            Product prd = product.get();

            cart.getItems().add(new ShoppingCartItem(prd.getId(),
                    prd.getName(), prd.getRestaurant().getId(),
                    prd.getRestaurant().getName(), item.getQuantity()));
        }

        cacheService.save(cart.getId(), cart);

        return getCart(cart.getId());
    }
}
