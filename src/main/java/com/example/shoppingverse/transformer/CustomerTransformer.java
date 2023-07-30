package com.example.shoppingverse.transformer;


import com.example.shoppingverse.dto.requestDto.CustomerRequestDto;
import com.example.shoppingverse.dto.responseDto.CustomerResponseDto;
import com.example.shoppingverse.model.Customer;
import lombok.experimental.UtilityClass;

// @UtilityClass  //for ensuring class having no object for other developer

public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .emailId(customerRequestDto.getEmailId())
                .mobileNo(customerRequestDto.getMobileNo())
                .gender(customerRequestDto.getGender())
                .build();

    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer savedCustomer){
        return CustomerResponseDto.builder()
                .name(savedCustomer.getName())
                .emailId(savedCustomer.getEmailId())
                .mobileNo(savedCustomer.getMobileNo())
                .gender(savedCustomer.getGender())
                .build();
    }


}
