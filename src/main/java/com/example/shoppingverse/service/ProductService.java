package com.example.shoppingverse.service;


import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Exception.SellerNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.ProductRequestDto;
import com.example.shoppingverse.dto.responseDto.ProductResponseDto;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.model.Seller;
import com.example.shoppingverse.repository.ProductRepository;
import com.example.shoppingverse.repository.SellerRepository;
import com.example.shoppingverse.transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    SellerRepository sellerRepository;


    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) {

        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmail());
        if(seller == null){
            throw new SellerNotFoundExceptions("seller is not found");
        }

        Product product = ProductTransformer.productRequestToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProduct().add(product);

        Seller savedSeller = sellerRepository.save(seller); // save the product and seller both because it is bidirectional mapping
        // and seller is the parent therefore we have to save parent only child will save automatically


        int size = savedSeller.getProduct().size(); // get product list size
        Product latestProduct = savedSeller.getProduct().get(size-1); // get the last product(latest saved product)

        return ProductTransformer.productToProductResponse(latestProduct);


    }

    public List<ProductResponseDto> getProdByCategoryAndPriceGreaterThan(ProductCategory category, int price) {
        List<Product> productList = productRepository.findAll();
        List<ProductResponseDto> responseDtoList = new ArrayList<>();

        for(Product product : productList){
            if(product.getCategory().equals(category) && product.getPrice()> price){
                ProductResponseDto response = ProductTransformer.productToProductResponse(product);
                responseDtoList.add(response);
            }
        }
        return responseDtoList;
    }
}
