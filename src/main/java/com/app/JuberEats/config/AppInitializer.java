package com.app.JuberEats.config;

import com.app.JuberEats.entity.orders.DeliveryMethod;
import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.entity.products.ProductType;
import com.app.JuberEats.entity.restaurants.Restaurant;
import com.app.JuberEats.entity.restaurants.RestaurantAddress;
import com.app.JuberEats.repositories.IDeliveryMethodRepository;
import com.app.JuberEats.repositories.IProductRepository;
import com.app.JuberEats.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("dev")
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IRestaurantRepository restaurantRepository;

    @Autowired
    private IDeliveryMethodRepository deliveryMethodRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application initialized.....");

        seedData();
    }

    private void seedData(){
        if(this.restaurantRepository.count() < 1){
            saveRestaurants();
        }

        if(this.productRepository.count() < 1){
            saveProducts();
        }

        if(this.deliveryMethodRepository.count() < 1){
            saveDeliveryMethods();
        }
    }

    private void saveRestaurants(){
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(null, "African Store",
                new RestaurantAddress(null, "Canning Road",
                        "Croydon", "CR0 9PA")));

        restaurants.add(new Restaurant(null, "Ichiba",
                new RestaurantAddress(null, "Westfield Centre",
                        "London", "W3L DP1")));

        this.restaurantRepository.saveAll(restaurants);
    }

    private void saveProducts(){
        ArrayList<Product> products = new ArrayList<>();

        List<Restaurant> restaurants = this.restaurantRepository.findAll();

        Restaurant ichiba = restaurants.stream()
                        .filter(res -> "Ichiba".equals(res.getName()))
                                .findFirst().get();

        Restaurant africanStore = restaurants.stream()
                .filter(res -> "African Store".equals(res.getName()))
                .findFirst().get();

        products.add(new Product(null, "Apple Juice", "",
                ProductType.DRINK, new BigDecimal(3.40), africanStore));

        products.add(new Product(null, "Jollof Rice", "",
                ProductType.FOOD, new BigDecimal(30.40), africanStore));

        products.add(new Product(null, "Japanese Sake", "",
                ProductType.DRINK, new BigDecimal(2.50), ichiba));

        products.add(new Product(null, "Assorted Salmon Rolls", "",
                ProductType.FOOD, new BigDecimal(16.40), ichiba));

        this.productRepository.saveAll(products);
    }

    private void saveDeliveryMethods(){
        ArrayList<DeliveryMethod> deliveryMethods = new ArrayList<>();

        deliveryMethods.add(new DeliveryMethod(null, "Standard Delivery",
                "3-4 Business Days", new BigDecimal(1.2)));

        deliveryMethods.add(new DeliveryMethod(null, "Fast Delivery",
                "1-2 Business Days", new BigDecimal(2.8)));

        deliveryMethods.add(new DeliveryMethod(null, "Swift Delivery",
                "Same Day Delivery", new BigDecimal(4.8)));

        this.deliveryMethodRepository.saveAll(deliveryMethods);
    }
}
