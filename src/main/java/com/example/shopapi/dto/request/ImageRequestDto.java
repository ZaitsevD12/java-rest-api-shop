package com.example.shopapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageRequestDto {
    @NotNull
    private MultipartFile imageFile;

    private String productId; // UUID as string
}