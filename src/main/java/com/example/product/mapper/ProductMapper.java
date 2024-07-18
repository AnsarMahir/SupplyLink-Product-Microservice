package com.example.product.mapper;

import com.example.product.dto.ProductDTO;
import com.example.product.model.Product;
import com.example.product.model.ProductCategory;


public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory().toString());
        dto.setPrice(product.getPrice());
        return dto;
    }

    public static Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCategory(ProductCategory.valueOf(dto.getCategory()));
        product.setPrice(dto.getPrice());
        return product;
    }
}

