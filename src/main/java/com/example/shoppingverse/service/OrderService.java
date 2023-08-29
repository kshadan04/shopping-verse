package com.example.shoppingverse.service;

import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.Exception.CustomerNotFoundExceptions;
import com.example.shoppingverse.Exception.InvalidCardExceptions;
import com.example.shoppingverse.Exception.ProductInsufficientExceptions;
import com.example.shoppingverse.Exception.ProductNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.OrderRequestDto;
import com.example.shoppingverse.dto.responseDto.OrderResponseDto;
import com.example.shoppingverse.model.*;
import com.example.shoppingverse.repository.*;
import com.example.shoppingverse.transformer.ItemTransformer;
import com.example.shoppingverse.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardService cardService;



    public OrderResponseDto placedOrder(OrderRequestDto orderRequestDto) {
        // check customer exist or not

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getCustomerEmail());
        if(customer == null){
            throw new CustomerNotFoundExceptions("Invalid Customer Email");
        }

        // check product exist or not
        Optional<Product> productOptional = productRepository.findById(orderRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundExceptions("Invalid product Id");
        }

        // check the Card exist or not

        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        Date todayDate = new Date();
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || card.getValidTill().after(todayDate)){
            throw new InvalidCardExceptions("Invalid card");
        }

        Product product = productOptional.get();
        if(product.getAvailableQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new ProductInsufficientExceptions("Required quantity is not available");
        }

        int newQuantity = product.getAvailableQuantity() - orderRequestDto.getRequiredQuantity();
        product.setAvailableQuantity(newQuantity);
        if(newQuantity == 0){
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }


        // requestDto to orderEntity


//        OrderEntity orderEntity = OrderTransformer.orderRequestDtoToOrder(orderRequestDto,product);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.generateMaskCard(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(orderRequestDto.getRequiredQuantity()*product.getPrice());

        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setOrderEntity(orderEntity);
        item.setProduct(product);

        orderEntity.setCustomer(customer);
        orderEntity.getItem().add(item);

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        product.getItem().add(savedOrder.getItem().get(0)); // only one item is placed
        customer.getOrderEntity().add(savedOrder);

        // prepare response dto
        return  OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }


    public OrderEntity placedOrder(Cart cart, Card card) {
        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(card.getCardNo());

        int orderTotal = 0;
        for(Item item : cart.getItem()){

            // check product available or not
            Product product = item.getProduct();
            if(product.getAvailableQuantity() < item.getRequiredQuantity()){
                throw new ProductInsufficientExceptions("Sorry! insufficient quantity for "+product.getProductName());
            }

            //new quantity
            int newQuantity = product.getAvailableQuantity()- item.getRequiredQuantity();
            product.setAvailableQuantity(newQuantity);
            if(newQuantity == 0) product.setStatus(ProductStatus.OUT_OF_STOCK);


            orderTotal += product.getPrice()*item.getRequiredQuantity();
            item.setOrderEntity(order);

        }

        order.setOrderTotal(orderTotal);
        order.setItem(cart.getItem());
        order.setCustomer(card.getCustomer());

        return order;

    }
}
