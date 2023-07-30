package com.example.shoppingverse.service;


import com.example.shoppingverse.dto.requestDto.CustomerRequestDto;
import com.example.shoppingverse.dto.responseDto.CustomerResponseDto;
import com.example.shoppingverse.model.Cart;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.shoppingverse.transformer.CustomerTransformer.customerRequestDtoToCustomer;
import static com.example.shoppingverse.transformer.CustomerTransformer.customerToCustomerResponseDto;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        // dto to entity;

//        Customer customer = new Customer();
//        customer.setName(customerRequestDto.getName());
//        customer.setEmailId(customerRequestDto.getEmailId());
//        customer.setMobileNo(customerRequestDto.getMobileNo());
//        customer.setGender(customerRequestDto.getGender());

        // by transform function
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);

        // cart must be initiate like a empty cart
        Cart cart = new Cart();
        cart.setCartTotal(0); // initialy nothing inside the cart
        cart.setCustomer(customer);

        customer.setCart(cart);

        // save the customer inside the database
        Customer savedCustomer = customerRepository.save(customer);

        // entity to request dto

//        CustomerResponseDto customerResponseDto = new CustomerResponseDto();
//        customerResponseDto.setName(savedCustomer.getName());
//        customerResponseDto.setEmailId(savedCustomer.getEmailId());
//        customerResponseDto.setMobileNo(savedCustomer.getMobileNo());
//        customerResponseDto.setGender(savedCustomer.getGender());

        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(savedCustomer);


        return customerResponseDto;
    }
}
