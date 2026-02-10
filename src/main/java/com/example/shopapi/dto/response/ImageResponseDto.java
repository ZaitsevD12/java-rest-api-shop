package com.example.shopapi.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class ImageResponseDto {
    private UUID id;
    private byte[] image;
}