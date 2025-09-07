package com.app.JuberEats.service;

import ch.qos.logback.core.joran.sanity.SanityChecker;
import com.app.JuberEats.entity.cart.ShoppingCart;
import com.app.JuberEats.entity.cart.ShoppingCartItem;
import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.entity.restaurants.Restaurant;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.repositories.IProductRepository;
import com.app.JuberEats.repositories.IRestaurantRepository;
import com.app.JuberEats.repositories.IShoppingCartRepository;
import com.app.JuberEats.request.product.CreateProductRequest;
import com.app.JuberEats.response.product.GetProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service()
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ICacheService cacheService;

    @Override
    public GetProductResponse getById(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);

        return productOpt
                .map((prd) -> mapper.map(prd, GetProductResponse.class))
                .orElseThrow(() ->
                        new NotFoundException("Product with ID: " + productId + " not found"));
    }

    @Override
    public List<GetProductResponse> getByRestaurant(Long restaurantId) {
        List<Product> restaurantProducts = productRepository.findByRestaurantId(restaurantId);

        return restaurantProducts.stream()
                .map((prd) -> mapper.map(prd, GetProductResponse.class))
                .toList();
    }

    @Override
    public Long createProduct(CreateProductRequest request) {
        Restaurant restaurant = restaurantRepository
                .findById(request.getRestaurantId())
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        Product product = new Product();
        product.setType(request.getProductType());
        product.setPrice(request.getPrice());
        product.setRestaurant(restaurant);
        product.setName(request.getName());

        Product savedProduct = productRepository.save(product);

        return savedProduct.getId();
    }
}
