package com.example.shoppingverse.controller;

import com.example.shoppingverse.dto.requestDto.CheckOutRequestDto;
import com.example.shoppingverse.dto.requestDto.ItemRequestDto;
import com.example.shoppingverse.dto.responseDto.CartResponseDto;
import com.example.shoppingverse.dto.responseDto.OrderResponseDto;
import com.example.shoppingverse.model.Item;
import com.example.shoppingverse.service.CartService;
import com.example.shoppingverse.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto,item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("checkout")
    public ResponseEntity checkOutCart(@RequestBody CheckOutRequestDto checkOutRequestDto){
        try{
            OrderResponseDto responseDto = cartService.checkOutCart(checkOutRequestDto);
            return new ResponseEntity(responseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return  new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
