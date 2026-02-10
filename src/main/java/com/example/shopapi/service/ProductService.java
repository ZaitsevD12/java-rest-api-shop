package com.example.shopapi.service;

import com.example.shopapi.dto.request.ProductRequestDto;
import com.example.shopapi.dto.response.ProductResponseDto;
import com.example.shopapi.entity.Product;
import com.example.shopapi.entity.Supplier;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.exception.ValidationException;
import com.example.shopapi.mapper.ProductMapper;
import com.example.shopapi.repository.ProductRepository;
import com.example.shopapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductResponseDto createProduct(ProductRequestDto dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId()).orElseThrow(() -> new NotFoundException("Supplier not found"));
        Product entity = productMapper.toEntity(dto, dto.getSupplierId());
        entity.setSupplier(supplier);
        Product saved = productRepository.save(entity);
        return productMapper.toDto(saved);
    }

    public ProductResponseDto decreaseStock(UUID id, int amount) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        int newStock = entity.getAvailableStock() - amount;
        if (newStock < 0) {
            throw new ValidationException("Insufficient stock");
        }
        entity.setAvailableStock(newStock);
        entity.setLastUpdateDate(LocalDate.now());
        Product updated = productRepository.save(entity);
        return productMapper.toDto(updated);
    }

    public ProductResponseDto getById(UUID id) {
        Product entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return productMapper.toDto(entity);
    }

    public List<ProductResponseDto> getAllAvailable() {
        List<Product> entities = productRepository.findByAvailableStockGreaterThan(0);
        return entities.stream().map(productMapper::toDto).collect(Collectors.toList());
    }

    public void deleteProduct(UUID id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}