package com.example.shoppingverse.service;

import com.example.shoppingverse.dto.requestDto.SellerRequestDto;
import com.example.shoppingverse.dto.responseDto.SellerResponseDto;
import com.example.shoppingverse.model.Seller;
import com.example.shoppingverse.repository.SellerRepository;
import com.example.shoppingverse.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        Seller seller = SellerTransformer.sellerRequestToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        return SellerTransformer.sellerToSellerResposeDto(savedSeller);
    }
}
