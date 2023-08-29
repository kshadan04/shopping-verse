package com.example.shoppingverse.dto.requestDto;


import com.example.shoppingverse.Enum.CardType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRequestDto {

    String customerMobileNo;

    String cardNo;

    CardType cardType;

    Date validTill;


    int cvv;


}
