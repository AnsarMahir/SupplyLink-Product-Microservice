package com.example.product.service;

import com.example.product.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;

public interface ProductService {
    Page<ProductDTO> getAllProducts(Pageable pageable);
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProductPartially(Long id, Map<String, Object> updates);
    ProductDTO deleteProduct(Long id);

    // New method for updating quantity
    ProductDTO updateProductQuantity(Long id, Integer quantity);
}