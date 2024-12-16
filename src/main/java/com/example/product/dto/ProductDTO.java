package com.example.product.dto;

public class ProductDTO {
    private Long id;
    private String name;
    private String category;
    private Double price;
    private String imageUrl;
    private Integer quantity; // New field added

    // Default constructor
    public ProductDTO() {}

    // Full constructor
    public ProductDTO(Long id, String name, String category, Double price, String imageUrl, Integer quantity) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
    }

    // Existing getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // New getter and setter for quantity
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}