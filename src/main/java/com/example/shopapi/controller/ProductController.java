package com.example.shopapi.controller;

import com.example.shopapi.dto.request.ProductRequestDto;
import com.example.shopapi.dto.response.ProductResponseDto;
import com.example.shopapi.dto.request.DecreaseStockDto;
import com.example.shopapi.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Operations with products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    @Operation(summary = "Add new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<ProductResponseDto> create(@Valid @RequestBody ProductRequestDto dto) {
        ProductResponseDto response = productService.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/stock")
    @Operation(summary = "Decrease product stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDto> decreaseStock(
            @Parameter(description = "Product ID") @PathVariable UUID id,
            @Valid @RequestBody DecreaseStockDto dto) {
        ProductResponseDto response = productService.decreaseStock(id, dto.getAmount());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<ProductResponseDto> getById(@Parameter(description = "Product ID") @PathVariable UUID id) {
        ProductResponseDto response = productService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all available products")
    public ResponseEntity<List<ProductResponseDto>> getAll() {
        List<ProductResponseDto> response = productService.getAllAvailable();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Product ID") @PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}