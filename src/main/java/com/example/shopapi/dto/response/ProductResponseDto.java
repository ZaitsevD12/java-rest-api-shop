package com.example.shopapi.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductResponseDto {
    private UUID id;
    private String name;
    private String category;
    private BigDecimal price;
    private Integer availableStock;
    private LocalDate lastUpdateDate;
    private UUID supplierId;
    private UUID imageId;
}