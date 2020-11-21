package com.example.spring.rest.services;

import com.example.spring.rest.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    Product getProduct(long id);

    Product addProduct(Product product);

    Product updateProduct(long id, Product product);

    Product deleteProduct(long id);
}
