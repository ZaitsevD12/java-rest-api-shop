package com.example.shopapi.dto.request;

import com.example.shopapi.entity.Client;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientRequestDto {
    @NotBlank
    private String clientName;

    @NotBlank
    private String clientSurname;

    private LocalDate birthday;

    private Client.Gender gender;

    private AddressRequestDto address;
}