package com.example.shopapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class SupplierRequestDto {
    @NotBlank
    private String name;

    private AddressRequestDto address;

    private String phoneNumber;
}