package com.example.shoppingverse.transformer;

import com.example.shoppingverse.dto.requestDto.CardRequestDto;
import com.example.shoppingverse.dto.responseDto.CardResponseDto;
import com.example.shoppingverse.model.Card;

public class CardTransformer {

    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .build();

    }

    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .validTill(card.getValidTill())
                .cardType(card.getCardType())
                .build();
    }
}
