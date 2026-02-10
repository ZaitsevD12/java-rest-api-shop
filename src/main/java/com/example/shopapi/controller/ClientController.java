package com.example.shopapi.controller;

import com.example.shopapi.dto.request.AddressRequestDto;
import com.example.shopapi.dto.request.ClientRequestDto;
import com.example.shopapi.dto.response.ClientResponseDto;
import com.example.shopapi.service.ClientService;
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
@RequestMapping("/api/v1/clients")
@Tag(name = "Clients", description = "Operations with clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping
    @Operation(summary = "Add new client", description = "Create a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<ClientResponseDto> create(@Valid @RequestBody ClientRequestDto dto) {
        ClientResponseDto response = clientService.createClient(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Client deleted"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<Void> delete(@Parameter(description = "Client ID") @PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Get clients by name and surname")
    public ResponseEntity<List<ClientResponseDto>> getByNameSurname(
            @Parameter(description = "Name") @RequestParam String name,
            @Parameter(description = "Surname") @RequestParam String surname) {
        List<ClientResponseDto> response = clientService.getClientsByNameSurname(name, surname);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(summary = "Get all clients", description = "With optional pagination")
    public ResponseEntity<List<ClientResponseDto>> getAll(
            @Parameter(description = "Limit") @RequestParam(required = false) Integer limit,
            @Parameter(description = "Offset") @RequestParam(required = false) Integer offset) {
        List<ClientResponseDto> response = clientService.getAllClients(limit, offset);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/address")
    @Operation(summary = "Update client address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated"),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<ClientResponseDto> updateAddress(@PathVariable UUID id, @Valid @RequestBody AddressRequestDto dto) {
        ClientResponseDto response = clientService.updateClientAddress(id, dto);
        return ResponseEntity.ok(response);
    }
}