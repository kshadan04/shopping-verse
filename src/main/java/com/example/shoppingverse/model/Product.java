package com.example.shoppingverse.model;

import com.example.shoppingverse.Enum.ProductCategory;
import com.example.shoppingverse.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String productName;

    int price;

    int availableQuantity;

    @Enumerated(EnumType.STRING)
    ProductCategory category;

    @Enumerated(EnumType.STRING)
    ProductStatus status;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> item = new ArrayList<>();
}

