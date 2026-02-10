package com.example.shopapi.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DecreaseStockDto {
    @NotNull
    @Min(1)
    private Integer amount;
}