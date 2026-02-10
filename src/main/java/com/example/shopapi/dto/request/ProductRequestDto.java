package com.example.shopapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ProductRequestDto {
    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    @Min(0)
    private BigDecimal price;

    private Integer availableStock = 0;

    private LocalDate lastUpdateDate;

    @NotNull
    private UUID supplierId;
}