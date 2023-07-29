package com.example.shoppingverse.controller;

import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/Customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;




}
