package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.responseDto.ItemResponseDto;
import com.example.shoppingverse.model.Item;

public class ItemTransformer {

    public static Item itemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .itemName(item.getProduct().getProductName())
                .itemPrice(item.getProduct().getPrice())
                .quantityAdded(item.getRequiredQuantity())
                .category(item.getProduct().getCategory())
                .build();
    }

}
