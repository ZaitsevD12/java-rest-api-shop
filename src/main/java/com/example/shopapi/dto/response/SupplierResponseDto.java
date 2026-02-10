package com.example.shopapi.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class SupplierResponseDto {
    private UUID id;
    private String name;
    private AddressResponseDto address;
    private String phoneNumber;
}