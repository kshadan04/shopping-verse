package com.example.shoppingverse.dto.responseDto;


import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import com.example.shoppingverse.model.Seller;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class ProductResponseDto {

    String sellerName;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory category;

    ProductStatus status;


}
