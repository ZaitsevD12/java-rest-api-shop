package com.example.shopapi.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class AddressResponseDto {
    private UUID id;
    private String country;
    private String city;
    private String street;
}