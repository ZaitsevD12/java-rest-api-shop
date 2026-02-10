package com.example.shopapi.mapper;

import com.example.shopapi.dto.request.AddressRequestDto;
import com.example.shopapi.dto.response.AddressResponseDto;
import com.example.shopapi.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toEntity(AddressRequestDto dto) {
        Address entity = new Address();
        entity.setCountry(dto.getCountry());
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        return entity;
    }

    public AddressResponseDto toDto(Address entity) {
        AddressResponseDto dto = new AddressResponseDto();
        dto.setId(entity.getId());
        dto.setCountry(entity.getCountry());
        dto.setCity(entity.getCity());
        dto.setStreet(entity.getStreet());
        return dto;
    }
}