package com.app.JuberEats.config;

import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.response.product.GetProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper =  new ModelMapper();

        modelMapper.typeMap(Product.class, GetProductResponse.class)
                .addMappings(mapper -> {
            mapper.map(Product::getId, GetProductResponse::setProductId);
            mapper.map(Product::getName, GetProductResponse::setName);
            mapper.map(Product::getPrice, GetProductResponse::setPrice);
            mapper.map(src -> src.getRestaurant().getName(),
                    GetProductResponse::setRestaurant);
        });

        return modelMapper;
    }
}
