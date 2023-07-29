package com.example.shoppingverse.model;


import com.example.shoppingverse.Enum.CardType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cardNo;

    int cvv;

    @CreationTimestamp
    Date validTill;

    CardType card;

    @ManyToOne
    @JoinColumn
    Customer customer;


}
