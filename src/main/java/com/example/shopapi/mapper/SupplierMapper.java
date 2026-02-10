package com.example.shopapi.mapper;

import com.example.shopapi.dto.request.SupplierRequestDto;
import com.example.shopapi.dto.response.SupplierResponseDto;
import com.example.shopapi.entity.Supplier;
import org.springframework.stereotype.Component;
import com.example.shopapi.dto.response.AddressResponseDto;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierRequestDto dto) {
        Supplier entity = new Supplier();
        entity.setName(dto.getName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }

    public SupplierResponseDto toDto(Supplier entity) {
        SupplierResponseDto dto = new SupplierResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        if (entity.getAddress() != null) {
            AddressResponseDto addrDto = new AddressResponseDto();
            addrDto.setId(entity.getAddress().getId());
            addrDto.setCountry(entity.getAddress().getCountry());
            addrDto.setCity(entity.getAddress().getCity());
            addrDto.setStreet(entity.getAddress().getStreet());
            dto.setAddress(addrDto);
        }
        return dto;
    }
}