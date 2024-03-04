package com.example.timeconverter.controller;

import com.example.timeconverter.entity.ConversionType;
import com.example.timeconverter.service.ConversionTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversionTypeController {
    private final ConversionTypeService service;

    public ConversionTypeController(ConversionTypeService service) {
        this.service = service;
    }

    @PostMapping("/conversionType")
    public ConversionType create(@RequestBody ConversionType conversionType) {
        return service.save(conversionType);
    }

    @GetMapping("/conversionType")
    public List<ConversionType> getAll() {
        return service.getAll();
    }

    @PutMapping("/conversionType/{id}")
    public ConversionType update(@PathVariable Long id, @RequestBody ConversionType updatedConversionType) {
        ConversionType conversionType = service.getById(id)
            .orElseThrow(() -> new RuntimeException("ConversionType not found with id " + id));

        // Обновить поля conversionType на основе updatedConversionType
        conversionType.setName(updatedConversionType.getName());

        return service.save(conversionType);
    }

    @DeleteMapping("/conversionType/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
