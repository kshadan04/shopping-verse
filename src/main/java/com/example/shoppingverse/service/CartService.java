package com.example.shoppingverse.service;


import com.example.shoppingverse.Exception.CustomerNotFoundExceptions;
import com.example.shoppingverse.Exception.EmptyCartExceptions;
import com.example.shoppingverse.Exception.InvalidCardExceptions;
import com.example.shoppingverse.dto.requestDto.CheckOutRequestDto;
import com.example.shoppingverse.dto.requestDto.ItemRequestDto;
import com.example.shoppingverse.dto.responseDto.CartResponseDto;
import com.example.shoppingverse.dto.responseDto.OrderResponseDto;
import com.example.shoppingverse.model.*;
import com.example.shoppingverse.repository.*;
import com.example.shoppingverse.transformer.CartTransformer;
import com.example.shoppingverse.transformer.OrderTransformer;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    public CartResponseDto addItemToCart(ItemRequestDto itemRequestDto, Item item) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());
        Product product = item.getProduct();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal() + product.getPrice()*item.getRequiredQuantity());
        item.setCart(cart);

        Item savedItem = itemRepository.save(item);
        cart.getItem().add(savedItem);

        product.getItem().add(savedItem);
        Cart saveCart = cartRepository.save(cart);

        productRepository.save(product);

        return CartTransformer.cartToCartResponseDto(saveCart);

    }

    public OrderResponseDto checkOutCart(CheckOutRequestDto checkOutRequestDto) {

        //check either person exist or not
        Customer customer = customerRepository.findByEmailId(checkOutRequestDto.getCutomerEmail());

        if(customer == null){
            throw new CustomerNotFoundExceptions("customer not exist");
        }

        //check card exist or not
        Card card = cardRepository.findByCardNo(checkOutRequestDto.getCardNo());
        Date todayDate = new Date();
        if(card == null || card.getCvv() != checkOutRequestDto.getCvv() || todayDate.before(card.getValidTill())){
            throw new InvalidCardExceptions("Invalid card");
        }

        Cart cart = customer.getCart();
        if(cart.getItem().size() == 0){
            throw new EmptyCartExceptions("cart is empty");
        }

        OrderEntity order = orderService.placedOrder(cart, card);
        resetCart(cart);

        OrderEntity savedOrder =  orderRepository.save(order);

        return OrderTransformer.OrderToOrderResponseDto(savedOrder);

    }

    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item : cart.getItem()){
            item.setCart(null);
        }
        cart.setItem(new ArrayList<>());
    }
}
