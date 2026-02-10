package com.example.shopapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequestDto {
    @NotBlank
    private String country;

    @NotBlank
    private String city;

    @NotBlank
    private String street;
}