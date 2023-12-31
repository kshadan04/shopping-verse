package com.example.shoppingverse.dto.requestDto;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutRequestDto {

    String cutomerEmail;

    String cardNo;

    int cvv;
}
