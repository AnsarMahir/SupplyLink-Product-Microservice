package com.example.product.controller;

import com.example.product.model.ProductCategory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @GetMapping
    public ProductCategory[] getAllCategories() {
        return ProductCategory.values();
    }
}
