package com.example.timeconverter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.ConversionType;
import com.example.timeconverter.repository.ConversionTypeRepository;

@Service
public class ConversionTypeService {
    private final ConversionTypeRepository repository;

    public ConversionTypeService(ConversionTypeRepository repository) {
        this.repository = repository;
    }

    public ConversionType save(ConversionType conversionType) {
        if (conversionType == null) {
            throw new IllegalArgumentException("ConversionType cannot be null");
        }
        return repository.save(conversionType);
    }

    public List<ConversionType> getAll() {
        return repository.findAll();
    }

    public ConversionType getByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("ConversionType not found with name " + name));
    }

    public Optional<ConversionType> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        repository.deleteById(id);
    }
}
