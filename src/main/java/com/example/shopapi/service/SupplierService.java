package com.example.shopapi.service;

import com.example.shopapi.dto.request.AddressRequestDto;
import com.example.shopapi.dto.request.SupplierRequestDto;
import com.example.shopapi.dto.response.SupplierResponseDto;
import com.example.shopapi.entity.Address;
import com.example.shopapi.entity.Supplier;
import com.example.shopapi.exception.NotFoundException;
import com.example.shopapi.exception.ValidationException;
import com.example.shopapi.mapper.AddressMapper;
import com.example.shopapi.mapper.SupplierMapper;
import com.example.shopapi.repository.AddressRepository;
import com.example.shopapi.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SupplierService {
    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private AddressMapper addressMapper;

    public SupplierResponseDto createSupplier(SupplierRequestDto dto) {
        if (dto.getAddress() == null) {
            throw new ValidationException("Address is required");
        }
        Address addr = addressMapper.toEntity(dto.getAddress());
        addr = addressRepository.save(addr);
        Supplier entity = supplierMapper.toEntity(dto);
        entity.setAddress(addr);
        Supplier saved = supplierRepository.save(entity);
        return supplierMapper.toDto(saved);
    }

    public SupplierResponseDto updateSupplierAddress(UUID id, AddressRequestDto addressDto) {
        Supplier entity = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        Address addr = addressMapper.toEntity(addressDto);
        addr = addressRepository.save(addr);
        entity.setAddress(addr);
        Supplier updated = supplierRepository.save(entity);
        return supplierMapper.toDto(updated);
    }

    public void deleteSupplier(UUID id) {
        if (!supplierRepository.existsById(id)) {
            throw new NotFoundException("Supplier not found");
        }
        supplierRepository.deleteById(id);
    }

    public List<SupplierResponseDto> getAllSuppliers() {
        List<Supplier> entities = supplierRepository.findAll();
        return entities.stream().map(supplierMapper::toDto).collect(Collectors.toList());
    }

    public SupplierResponseDto getSupplierById(UUID id) {
        Supplier entity = supplierRepository.findById(id).orElseThrow(() -> new NotFoundException("Supplier not found"));
        return supplierMapper.toDto(entity);
    }
}