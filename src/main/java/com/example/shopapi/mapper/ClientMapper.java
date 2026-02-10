package com.example.shopapi.mapper;

import com.example.shopapi.dto.request.ClientRequestDto;
import com.example.shopapi.dto.response.ClientResponseDto;
import com.example.shopapi.entity.Client;
import org.springframework.stereotype.Component;
import com.example.shopapi.dto.response.AddressResponseDto;

import java.time.LocalDate;

@Component
public class ClientMapper {
    public Client toEntity(ClientRequestDto dto) {
        Client entity = new Client();
        entity.setClientName(dto.getClientName());
        entity.setClientSurname(dto.getClientSurname());
        entity.setBirthday(dto.getBirthday());
        entity.setGender(dto.getGender());
        return entity;
    }

    public ClientResponseDto toDto(Client entity) {
        ClientResponseDto dto = new ClientResponseDto();
        dto.setId(entity.getId());
        dto.setClientName(entity.getClientName());
        dto.setClientSurname(entity.getClientSurname());
        dto.setBirthday(entity.getBirthday());
        dto.setGender(entity.getGender() != null ? entity.getGender().name() : null);
        dto.setRegistrationDate(entity.getRegistrationDate());
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