package com.example.product.service;

import com.example.product.model.Product;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product createProduct(Product product);
    Optional<Product> updateProductPartially(Long id, Map<String, Object> updates);
    void deleteProduct(Long id);
}

