package com.example.timeconverter.controller;

import com.example.timeconverter.entity.Timezone;
import com.example.timeconverter.service.TimezoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timezone")
public class TimezoneController {
    private final TimezoneService service;

    public TimezoneController(TimezoneService service) {
        this.service = service;
    }

    @PostMapping
    public Timezone create(@RequestBody Timezone timezone) {
        return service.save(timezone);
    }

    @GetMapping
    public List<Timezone> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public Timezone update(@PathVariable Long id, @RequestBody Timezone updatedtimezone) {
        Timezone timezone = service.getById(id)
            .orElseThrow(() -> new RuntimeException("timezone not found with id " + id));

        timezone.setName(updatedtimezone.getName());
        return service.save(timezone);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
