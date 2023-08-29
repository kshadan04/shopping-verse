package com.example.shoppingverse.dto.responseDto;


import com.example.shoppingverse.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder

public class CardResponseDto {

    String customerName;

    String cardNo; // masked --> XXXX XX23 5647

    Date validTill;

    CardType cardType;
}
