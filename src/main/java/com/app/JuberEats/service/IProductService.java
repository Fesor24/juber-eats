package com.app.JuberEats.service;

import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.request.product.CreateProductRequest;
import com.app.JuberEats.response.product.GetProductResponse;
import org.springframework.stereotype.Service;
import java.util.List;

public interface IProductService {
    GetProductResponse getById(Long productId);
    List<GetProductResponse> getByRestaurant(Long restaurantId);
    Long createProduct(CreateProductRequest request);
}
