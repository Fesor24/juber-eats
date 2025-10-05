package com.app.JuberEats.service;

import com.app.JuberEats.entity.orders.*;
import com.app.JuberEats.entity.products.Product;
import com.app.JuberEats.exceptions.BadRequestException;
import com.app.JuberEats.exceptions.NotFoundException;
import com.app.JuberEats.repositories.IDeliveryMethodRepository;
import com.app.JuberEats.repositories.IOrderRepository;
import com.app.JuberEats.repositories.IProductRepository;
import com.app.JuberEats.request.order.CreateOrderRequest;
import com.app.JuberEats.response.cart.GetShoppingCartResponse;
import com.app.JuberEats.response.cart.ShoppingCartItemResponse;
import com.app.JuberEats.response.order.GetOrderResponse;
import com.app.JuberEats.utils.JsonConverter;
import com.app.JuberEats.utils.OrderProductDetail;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IProductRepository productRepository;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @Autowired
    private IDeliveryMethodRepository deliveryMethodRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public GetOrderResponse create(CreateOrderRequest request) {
        GetShoppingCartResponse cart = shoppingCartService
                .getCart(request.getCartId());

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal subTotal = BigDecimal.ZERO;

        if(cart.getItems().isEmpty()){
            throw new NotFoundException("No product item found in cart");
        }

        Optional<DeliveryMethod> deliveryMethodOpt = deliveryMethodRepository
                .findById(request.getDeliveryMethodId());

        if(deliveryMethodOpt.isEmpty()){
            throw new NotFoundException("Delivery method with ID:" +
                    request.getDeliveryMethodId() + " not found");
        }

        DeliveryMethod deliveryMethod = deliveryMethodOpt.get();

        List<Long> productIds = cart.getItems()
                .stream()
                .map(cartItem -> cartItem.getProductId())
                .toList();

        List<Product> products = productRepository.findByIdIn(productIds);

        for(ShoppingCartItemResponse item: cart.getItems()){
            Optional<Product> productOpt = products.stream()
                    .filter((prd) -> prd.getId() == item.getProductId())
                    .findFirst();

            if(productOpt.isEmpty()){
                throw new NotFoundException("Product with ID:" +
                        item.getProductId() + " not found");
            }

            Product prd = productOpt.get();

            OrderProductDetail productDetail = new OrderProductDetail();
            productDetail.setProductName(prd.getName());
            productDetail.setPrice(prd.getPrice());

            subTotal.add(prd.getPrice());

            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(item.getQuantity());
            orderItem.setProduct(JsonConverter.serialize(productDetail));

            orderItems.add(orderItem);
        }

        OrderAddress orderAddress = new OrderAddress();
        orderAddress.setCity(request.getAddress().getCity());
        orderAddress.setStreet(request.getAddress().getStreet());
        orderAddress.setPostCode(request.getAddress().getPostCode());

        // to be changed...
        String userEmail = "test@mail.com";

        BigDecimal totalPrice = subTotal.add(deliveryMethod.getPrice());

        Order order = new Order(userEmail, subTotal, deliveryMethod, totalPrice,
                orderItems, orderAddress);

        orderRepository.save(order);

        return mapper.map(order, GetOrderResponse.class);
    }

    @Override
    public void cancelOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        if(orderOpt.isEmpty()){
            throw new NotFoundException("Order with ID: " + orderId + " not found");
        }

        Order order = orderOpt.get();

        if(!(order.getStatus() == OrderStatus.PENDING || order.getStatus() == OrderStatus.PAID)){
            throw new BadRequestException("Order can not be cancelled");
        }

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

    @Override
    public GetOrderResponse getOrder(Long orderId) {
        Optional<Order> orderOpt = orderRepository.findById(orderId);

        return orderOpt.map(ord -> mapper.map(ord, GetOrderResponse.class))
                .orElseThrow(() ->
                        new NotFoundException("Order with Id:" + orderId + " not found"));
    }
}
