package com.example.shoppingverse.transformer;


import com.example.shoppingverse.dto.requestDto.OrderRequestDto;
import com.example.shoppingverse.dto.responseDto.ItemResponseDto;
import com.example.shoppingverse.dto.responseDto.OrderResponseDto;
import com.example.shoppingverse.model.Item;
import com.example.shoppingverse.model.OrderEntity;
import com.example.shoppingverse.model.Product;
import jakarta.persistence.criteria.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
//
//    public static OrderEntity orderRequestDtoToOrder(OrderRequestDto orderRequestDto, Product product){
//        return OrderEntity.builder()
//                .orderId(String.valueOf(UUID.randomUUID()))
//                .orderTotal(orderRequestDto.getProductId()*product.getPrice())
//                .cardUsed(orderRequestDto.getCardUsed())
//                .build();
//    }

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto> itemList = new ArrayList<>();
        for(Item item : orderEntity.getItem()){
            itemList.add(ItemTransformer.itemToItemResponseDto(item));
        }
        return OrderResponseDto.builder()
                .orderId(orderEntity.getOrderId())
                .orderDate(orderEntity.getOrderDate())
                .orderTotal(orderEntity.getOrderTotal())
                .customerName(orderEntity.getCustomer().getName())
                .items(itemList)
                .build();
    }
}
