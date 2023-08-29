package com.example.shoppingverse.dto.requestDto;


import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.model.Seller;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {

    String sellerEmail;

    String productName;

    int price;

    int availableQuantity;

    ProductCategory category;
}
