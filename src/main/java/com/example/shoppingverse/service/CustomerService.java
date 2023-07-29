package com.example.shoppingverse.service;


import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


}
