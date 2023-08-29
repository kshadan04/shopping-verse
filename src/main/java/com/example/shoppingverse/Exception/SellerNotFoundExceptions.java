package com.example.shoppingverse.Exception;

import com.example.shoppingverse.repository.SellerRepository;

public class SellerNotFoundExceptions extends RuntimeException{

    public SellerNotFoundExceptions(String message){
        super(message);
    }
}
