package com.example.shoppingverse.dto.responseDto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Builder
public class OrderResponseDto {

    String orderId;

    Date orderDate;

    String cardUsed;

    int orderTotal;

    String customerName;

    List<ItemResponseDto> items;
}
