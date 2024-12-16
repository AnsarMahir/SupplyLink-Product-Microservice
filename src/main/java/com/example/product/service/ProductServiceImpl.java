package com.example.product.service;

import com.example.product.dto.ProductDTO;
import com.example.product.exception.*;
import com.example.product.mapper.ProductMapper;
import com.example.product.model.Product;
import com.example.product.model.ProductCategory;
import com.example.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Page<ProductDTO> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        return productsPage.map(ProductMapper::toDTO);
    }

    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toDTO);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        try {
            Product product = ProductMapper.toEntity(productDTO);
            Product savedProduct = productRepository.save(product);
            return ProductMapper.toDTO(savedProduct);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Failed to create product: " + e.getMessage());
        }
    }

    public ProductDTO deleteProduct(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

        Product product = productOptional.get();
        productRepository.delete(product);
        return ProductMapper.toDTO(product);
    }

    public ProductDTO updateProductPartially(Long id, Map<String, Object> updates) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with id " + id);
        }

        Product product = productOptional.get();
        updates.forEach((key, value) -> {
            switch (key) {
                case "name" -> product.setName((String) value);
                case "category" -> product.setCategory(ProductCategory.valueOf((String) value));
                case "price" -> product.setPrice((Double) value);
                case "imageUrl" -> product.setImageUrl((String) value);
                case "quantity" -> product.setQuantity((Integer) value); // Added quantity update
            }
        });

        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public ProductDTO updateProductQuantity(Long id, Integer quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        // Validate quantity is not negative
        if (quantity < 0) {
            throw new InvalidRequestException("Quantity cannot be negative");
        }

        product.setQuantity(quantity);
        Product updatedProduct = productRepository.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }
}