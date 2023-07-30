package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.requestDto.SellerRequestDto;
import com.example.shoppingverse.dto.responseDto.SellerResponseDto;
import com.example.shoppingverse.model.Seller;

public class SellerTransformer {

    public static Seller sellerRequestToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .panNo(sellerRequestDto.getPanNo())
                .build();
    }

    public  static SellerResponseDto sellerToSellerResposeDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
