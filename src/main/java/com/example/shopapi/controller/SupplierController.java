package com.example.shopapi.controller;

import com.example.shopapi.dto.request.AddressRequestDto;
import com.example.shopapi.dto.request.SupplierRequestDto;
import com.example.shopapi.dto.response.SupplierResponseDto;
import com.example.shopapi.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/suppliers")
@Tag(name = "Suppliers", description = "Operations with suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping
    @Operation(summary = "Add new supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Supplier created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<SupplierResponseDto> create(@Valid @RequestBody SupplierRequestDto dto) {
        SupplierResponseDto response = supplierService.createSupplier(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}/address")
    @Operation(summary = "Update supplier address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<SupplierResponseDto> updateAddress(
            @Parameter(description = "Supplier ID") @PathVariable UUID id,
            @Valid @RequestBody AddressRequestDto dto) {
        SupplierResponseDto response = supplierService.updateSupplierAddress(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Supplier deleted"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Supplier ID") @PathVariable UUID id) {
        supplierService.deleteSupplier(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all suppliers")
    public ResponseEntity<List<SupplierResponseDto>> getAll() {
        List<SupplierResponseDto> response = supplierService.getAllSuppliers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get supplier by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Supplier found"),
            @ApiResponse(responseCode = "404", description = "Supplier not found")
    })
    public ResponseEntity<SupplierResponseDto> getById(@Parameter(description = "Supplier ID") @PathVariable UUID id) {
        SupplierResponseDto response = supplierService.getSupplierById(id);
        return ResponseEntity.ok(response);
    }
}