package com.example.shoppingverse.controller;


import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Exception.SellerNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.ProductRequestDto;
import com.example.shoppingverse.dto.responseDto.ProductResponseDto;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.service.ProductService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){


        try {
            ProductResponseDto response = productService.addProduct(productRequestDto);
            return new ResponseEntity(response,HttpStatus.CREATED);
        }
        catch (SellerNotFoundExceptions e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("get_by_category_price_greater_than")
    public ResponseEntity getProdByCategoryAndPriceGreaterThan(@RequestParam("category") ProductCategory category,
                                                               @RequestParam("price") int price){
        List<ProductResponseDto> productResponseDtos = productService.getProdByCategoryAndPriceGreaterThan(category, price);
        return new ResponseEntity(productResponseDtos,HttpStatus.FOUND);

    }
}
