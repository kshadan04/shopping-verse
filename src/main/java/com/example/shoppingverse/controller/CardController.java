package com.example.shoppingverse.controller;


import com.example.shoppingverse.Exception.CustomerNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.CardRequestDto;
import com.example.shoppingverse.dto.responseDto.CardResponseDto;
import com.example.shoppingverse.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto responseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }
        catch (CustomerNotFoundExceptions e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
