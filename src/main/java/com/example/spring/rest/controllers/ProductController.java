package com.example.spring.rest.controllers;

import com.example.spring.rest.models.Product;
import com.example.spring.rest.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Product> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private Product get(@PathVariable long id) {
        return productService.getProduct(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private Product add(Product product) {
        return productService.addProduct(product);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    private Product update(@PathVariable long id, Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "/{id}")
    private Product delete(@PathVariable long id) {
        return productService.deleteProduct(id);
    }
}
