package com.example.shoppingverse.service;

import com.example.shoppingverse.Exception.CustomerNotFoundExceptions;
import com.example.shoppingverse.Exception.ProductInsufficientExceptions;
import com.example.shoppingverse.Exception.ProductNotFoundExceptions;
import com.example.shoppingverse.dto.requestDto.ItemRequestDto;
import com.example.shoppingverse.model.Customer;
import com.example.shoppingverse.model.Item;
import com.example.shoppingverse.model.Product;
import com.example.shoppingverse.repository.CustomerRepository;
import com.example.shoppingverse.repository.ProductRepository;
import com.example.shoppingverse.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public Item createItem(ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmail());

        if(customer == null){
            throw new CustomerNotFoundExceptions("please enter the valid email id");
        }

        // check product id exist or not

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundExceptions("product does not exist");
        }

        // found the product
        Product product = productOptional.get();

        // check required quantity exist or not

        if(product.getAvailableQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new ProductInsufficientExceptions("Product is not sufficient to add in cart");
        }

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        item.setProduct(product);

        return item;

    }
}
