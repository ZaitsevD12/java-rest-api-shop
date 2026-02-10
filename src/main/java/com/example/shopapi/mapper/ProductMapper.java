package com.example.shopapi.mapper;

import com.example.shopapi.dto.request.ProductRequestDto;
import com.example.shopapi.dto.response.ProductResponseDto;
import com.example.shopapi.entity.Product;
import org.springframework.stereotype.Component;
import java.util.UUID;

import java.time.LocalDate;

@Component
public class ProductMapper {
    public Product toEntity(ProductRequestDto dto, UUID supplierId) {
        Product entity = new Product();
        entity.setName(dto.getName());
        entity.setCategory(dto.getCategory());
        entity.setPrice(dto.getPrice());
        entity.setAvailableStock(dto.getAvailableStock());
        entity.setLastUpdateDate(dto.getLastUpdateDate() != null ? dto.getLastUpdateDate() : LocalDate.now());
        return entity;
    }

    public ProductResponseDto toDto(Product entity) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setAvailableStock(entity.getAvailableStock());
        dto.setLastUpdateDate(entity.getLastUpdateDate());
        if (entity.getSupplier() != null) dto.setSupplierId(entity.getSupplier().getId());
        if (entity.getImage() != null) dto.setImageId(entity.getImage().getId());
        return dto;
    }
}