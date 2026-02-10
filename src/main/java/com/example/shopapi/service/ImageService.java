package com.example.shopapi.service;

import com.example.shopapi.entity.Image;
import com.example.shopapi.entity.Product;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.exception.ValidationException;
import com.example.shopapi.mapper.ImageMapper;
import com.example.shopapi.repository.ImageRepository;
import com.example.shopapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageMapper imageMapper;

    public void addImage(byte[] imageData, UUID productId) {
        if (imageData == null || imageData.length == 0) {
            throw new ValidationException("Image data cannot be empty");
        }
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        UUID imageId = UUID.randomUUID();
        Image image = imageMapper.toEntity(imageData, imageId);
        imageRepository.save(image);
        product.setImage(image);
        productRepository.save(product);
    }

    public void updateImage(UUID id, byte[] imageData) {
        if (imageData == null || imageData.length == 0) {
            throw new ValidationException("Image data cannot be empty");
        }
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found"));
        image.setImage(imageData);
        imageRepository.save(image);
    }

    public void deleteImage(UUID id) {
        if (!imageRepository.existsById(id)) {
            throw new NotFoundException("Image not found");
        }
        imageRepository.deleteById(id);
    }

    public byte[] getByProductId(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found"));
        Image image = product.getImage();
        if (image == null) {
            throw new NotFoundException("No image for this product");
        }
        return image.getImage();
    }

    public byte[] getImageData(UUID id) {
        Image image = imageRepository.findById(id).orElseThrow(() -> new NotFoundException("Image not found"));
        return image.getImage();
    }
}