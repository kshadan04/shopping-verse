package com.example.shoppingverse.model;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Order_info")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int orderTotal;

    @CreationTimestamp
    Date orderDate;

    String orderId;

    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    List<Item> item = new ArrayList<>();
}
