package com.example.shoppingverse.Exception;

import com.example.shoppingverse.model.Customer;

public class CustomerNotFoundExceptions extends RuntimeException{
    public  CustomerNotFoundExceptions(String message){
        super(message);
    }
}
