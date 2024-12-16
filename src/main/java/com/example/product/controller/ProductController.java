package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.exception.InvalidRequestException;
import com.example.product.exception.ResourceNotFoundException;
import com.example.product.exception.SuccessResponse;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController extends AbstractController{

    private final ProductService productService;



    @GetMapping
    public ResponseEntity<SuccessResponse<List<ProductDTO>>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> productPage = productService.getAllProducts(pageable);
        return createSuccessResponse(productPage.getContent(), "Products fetched successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDTO>> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getProductById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));
        return createSuccessResponse(productDTO, "Product fetched successfully", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessResponse<ProductDTO>> createProduct(@RequestBody ProductDTO productDTO) {
        // Validate quantity is not negative
        if (productDTO.getQuantity() != null && productDTO.getQuantity() < 0) {
            throw new InvalidRequestException("Quantity cannot be negative");
        }

        ProductDTO createdProduct = productService.createProduct(productDTO);
        return createSuccessResponse(createdProduct, "Product created successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDTO>> updateProductPartially(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates
    ) {
        // Optional validation for quantity
        if (updates.containsKey("quantity")) {
            Object quantityValue = updates.get("quantity");
            if (quantityValue instanceof Number) {
                int quantity = ((Number) quantityValue).intValue();
                if (quantity < 0) {
                    throw new InvalidRequestException("Quantity cannot be negative");
                }
            }
        }

        ProductDTO updatedProduct = productService.updateProductPartially(id, updates);
        return createSuccessResponse(updatedProduct, "Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<ProductDTO>> deleteProduct(@PathVariable Long id) {
        ProductDTO deletedProduct = productService.deleteProduct(id);
        return createSuccessResponse(deletedProduct, "Product deleted successfully", HttpStatus.OK);
    }

    // New endpoint to update product quantity
    @PatchMapping("/{id}/quantity")
    public ResponseEntity<SuccessResponse<ProductDTO>> updateProductQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        if (quantity < 0) {
            throw new InvalidRequestException("Quantity cannot be negative");
        }

        ProductDTO updatedProduct = productService.updateProductQuantity(id, quantity);
        return createSuccessResponse(updatedProduct, "Product quantity updated successfully", HttpStatus.OK);
    }
}

