package com.example.shoppingverse.transformer;

import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.dto.requestDto.ProductRequestDto;
import com.example.shoppingverse.dto.responseDto.ProductResponseDto;
import com.example.shoppingverse.model.Product;

public class ProductTransformer {

    public static Product productRequestToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .availableQuantity(productRequestDto.getAvailableQuantity())
                .category(productRequestDto.getCategory())
                .status(ProductStatus.AVAILABLE)
                .build();

    }

    public static ProductResponseDto productToProductResponse(Product product){
        return ProductResponseDto.builder()
                .sellerName(product.getSeller().getName())
                .productName(product.getProductName())
                .price(product.getPrice())
                .availableQuantity(product.getAvailableQuantity())
                .category(product.getCategory())
                .status(product.getStatus())
                .build();
    }
}
