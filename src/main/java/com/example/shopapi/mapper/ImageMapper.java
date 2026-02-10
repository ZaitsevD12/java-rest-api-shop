package com.example.shopapi.mapper;

import com.example.shopapi.entity.Image;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ImageMapper {
    public Image toEntity(byte[] imageData, UUID id) {
        Image entity = new Image();
        entity.setId(id);
        entity.setImage(imageData);
        return entity;
    }
}