package com.example.shoppingverse.controller;

import com.example.shoppingverse.dto.requestDto.OrderRequestDto;
import com.example.shoppingverse.dto.responseDto.OrderResponseDto;
import com.example.shoppingverse.repository.OrderRepository;
import com.example.shoppingverse.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try{
            OrderResponseDto response = orderService.placedOrder(orderRequestDto);
            return new ResponseEntity(response, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
