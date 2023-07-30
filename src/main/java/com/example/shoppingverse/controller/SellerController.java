package com.example.shoppingverse.controller;

import com.example.shoppingverse.dto.requestDto.SellerRequestDto;
import com.example.shoppingverse.dto.responseDto.SellerResponseDto;
import com.example.shoppingverse.model.Seller;
import com.example.shoppingverse.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto response = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
