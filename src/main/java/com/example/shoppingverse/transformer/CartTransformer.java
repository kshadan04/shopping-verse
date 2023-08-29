package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.responseDto.CartResponseDto;
import com.example.shoppingverse.dto.responseDto.ItemResponseDto;
import com.example.shoppingverse.model.Cart;
import com.example.shoppingverse.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto cartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : cart.getItem()){
            itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));
        }
        return CartResponseDto.builder()
                .customerName(cart.getCustomer().getName())
                .cartTotal(cart.getCartTotal())
                .items(itemResponseDtoList)
                .build();
    }
}
