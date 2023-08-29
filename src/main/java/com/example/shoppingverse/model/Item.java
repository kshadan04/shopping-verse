package com.example.shoppingverse.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ManyToAny;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Builder
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int requiredQuantity;

    @ManyToOne
    @JoinColumn
    Product product;

    @ManyToOne
    @JoinColumn
    Cart cart;

    @ManyToOne
    @JoinColumn
    OrderEntity orderEntity;

}
