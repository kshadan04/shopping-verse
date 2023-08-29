package com.example.shoppingverse.service;

import com.example.shoppingverse.Exception.CustomerNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.CardRequestDto;
import com.example.shoppingverse.dto.responseDto.CardResponseDto;
import com.example.shoppingverse.model.Card;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.repository.CardRepository;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CardService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    public CardResponseDto addCard(CardRequestDto cardRequestDto) {

        Customer customer = customerRepository.findByMobileNo(cardRequestDto.getCustomerMobileNo());

        if(customer == null){
            throw new CustomerNotFoundExceptions("Customer no found");
        }

        // request to card
        Card card = CardTransformer.cardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer); // set the customer
        customer.getCard().add(card); // add the card inside the customer

        // save the card and customer
//        Card saveCard = cardRepository.save(card); // cant set because it is the child entity

        Customer savedCustomer = customerRepository.save(customer);

        // latestCard
        List<Card> cards = savedCustomer.getCard();
        Card latestCard = cards.get(cards.size()-1);


        CardResponseDto cardResponseDto = CardTransformer.cardToCardResponseDto(latestCard);

        cardResponseDto.setCardNo(generateMaskCard(latestCard.getCardNo()));
        return cardResponseDto;


    }

    public String generateMaskCard(String cardNo){
        int cardLength = cardNo.length();

        String maskCard = "";
        for(int i=0; i<cardLength-4; i++){
            maskCard += 'X';
        }

        maskCard += cardNo.substring(cardLength-4);

        return maskCard;
    }
}
