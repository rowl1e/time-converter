package com.example.timeconverter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.ConversionHistory;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.repository.ConversionHistoryRepository;

@Service
public class ConversionHistoryService {
    private final ConversionHistoryRepository repository;

    public ConversionHistoryService(ConversionHistoryRepository repository) {
        this.repository = repository;
    }

    public ConversionHistory save(ConversionHistory conversionHistory) {
        if (conversionHistory == null) {
            throw new IllegalArgumentException("ConversionHistory cannot be null");
        }
        return repository.save(conversionHistory);
    }

    public List<ConversionHistory> getAll() {
        return repository.findAll();
    } 

    public Optional<ConversionHistory> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return repository.findById(id);
    }

    public List<ConversionHistory> getAllByTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        return repository.findAllByTagsContains(tag);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        repository.deleteById(id);
    }    
} 
