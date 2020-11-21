package com.example.spring.rest.services;

import com.example.spring.rest.models.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Long, Product> productMap;

    static {
        productMap = new HashMap<>();
        productMap.put(1L, new Product(1, "Pen", new BigDecimal(100)));
        productMap.put(2L, new Product(2, "NoteBook", new BigDecimal(200)));
        productMap.put(3L, new Product(3, "Duster", new BigDecimal(300)));
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product getProduct(long id) {
        return productMap.get(id);
    }

    @Override
    public Product addProduct(Product product) {
        return productMap.put(product.getId(), product);
    }

    @Override
    public Product updateProduct(long id, Product product) {
        Product oldProduct = productMap.get(id);
        if (Objects.isNull(oldProduct))
            throw new RuntimeException("Product not found. Invalid id: " + id);
        oldProduct.setName(product.getName());
        oldProduct.setPrice(product.getPrice());
        return oldProduct;
    }

    @Override
    public Product deleteProduct(long id) {
        return productMap.remove(id);
    }
}
