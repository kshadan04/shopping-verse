package com.example.shoppingverse.repository;

import com.example.shoppingverse.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository                                          // parent class & its p.k.
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}
