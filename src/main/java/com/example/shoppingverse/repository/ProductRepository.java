package com.example.shoppingverse.repository;

import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {

}
