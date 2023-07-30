package com.example.shoppingverse.dto.responseDto;


import com.example.shoppingverse.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class CustomerResponseDto {

    String name;

    String emailId;

    String mobileNo;

    Gender gender;
}
