package com.example.shopapi.service;

import com.example.shopapi.dto.request.AddressRequestDto;
import com.example.shopapi.dto.request.ClientRequestDto;
import com.example.shopapi.dto.response.ClientResponseDto;
import com.example.shopapi.entity.Address;
import com.example.shopapi.entity.Client;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.exception.ValidationException;
import com.example.shopapi.mapper.AddressMapper;
import com.example.shopapi.mapper.ClientMapper;
import com.example.shopapi.repository.AddressRepository;
import com.example.shopapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private AddressMapper addressMapper;

    public ClientResponseDto createClient(ClientRequestDto dto) {
        if (dto.getAddress() == null) {
            throw new ValidationException("Address is required");
        }
        Address addr = addressMapper.toEntity(dto.getAddress());
        addr = addressRepository.save(addr);
        Client entity = clientMapper.toEntity(dto);
        entity.setAddress(addr);
        entity.setRegistrationDate(LocalDate.now());
        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    public void deleteClient(UUID id) {
        if (!clientRepository.existsById(id)) {
            throw new NotFoundException("Client not found with id: " + id);
        }
        clientRepository.deleteById(id);
    }

    public List<ClientResponseDto> getClientsByNameSurname(String name, String surname) {
        List<Client> entities = clientRepository.findByClientNameAndClientSurname(name, surname);
        return entities.stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    public List<ClientResponseDto> getAllClients(Integer limit, Integer offset) {
        List<Client> entities;
        if (limit != null && offset != null) {
            entities = clientRepository.findAllWithPagination(offset, limit);
        } else {
            entities = clientRepository.findAll();
        }
        return entities.stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    public ClientResponseDto updateClientAddress(UUID id, AddressRequestDto addressDto) {
        Client entity = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client not found"));
        Address addr = addressMapper.toEntity(addressDto);
        addr = addressRepository.save(addr);
        entity.setAddress(addr);
        Client updated = clientRepository.save(entity);
        return clientMapper.toDto(updated);
    }
}