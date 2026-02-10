package com.example.shopapi.controller;

import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    public static class AddImageRequest {
        @Schema(type = "string", format = "binary", description = "Image file", required = true)
        private MultipartFile file;

        @Schema(description = "Product ID", type = "string", required = true)
        private String productId;

        // Getters and setters
        public MultipartFile getFile() {
            return file;
        }

        public void setFile(MultipartFile file) {
            this.file = file;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Add image for product",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                            schema = @Schema(implementation = AddImageRequest.class)
                    )
            ))
    public ResponseEntity<String> addImage(@ModelAttribute AddImageRequest request) {
        try {
            byte[] bytes = request.getFile().getBytes();
            UUID productId = UUID.fromString(request.getProductId());
            imageService.addImage(bytes, productId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image added");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Update image")
    public ResponseEntity<String> updateImage(
            @Parameter(description = "Image ID") @PathVariable UUID id,
            @Parameter(description = "Image file") @RequestParam("file") @Schema(type = "string", format = "binary") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            imageService.updateImage(id, bytes);
            return ResponseEntity.ok("Image updated");
        } catch (IOException e) {
            throw new RuntimeException("Failed to read image file", e);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete image")
    public ResponseEntity<Void> deleteImage(@Parameter(description = "Image ID") @PathVariable UUID id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get image by product")
    public ResponseEntity<ByteArrayResource> getByProduct(@Parameter(description = "Product ID") @PathVariable UUID productId) {
        byte[] imageData = imageService.getByProductId(productId);
        ByteArrayResource resource = new ByteArrayResource(imageData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image by id")
    public ResponseEntity<ByteArrayResource> getById(@Parameter(description = "Image ID") @PathVariable UUID id) {
        byte[] imageData = imageService.getImageData(id);
        if (imageData == null) throw new NotFoundException("Image not found");
        ByteArrayResource resource = new ByteArrayResource(imageData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}