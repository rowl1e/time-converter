package com.example.timeconverter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.Timezone;
import com.example.timeconverter.repository.TimezoneRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TimezoneService {
    private final TimezoneRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TimezoneService.class);

    public TimezoneService(TimezoneRepository repository) {
        this.repository = repository;
    }

    public Timezone save(Timezone timezone) {
        if (timezone == null) {
            LOGGER.error("Attempted to save null timezone");
            throw new IllegalArgumentException("Timezone cannot be null");
        }
        LOGGER.info("Saving timezone {}", timezone);
        return repository.save(timezone);
    }

    public List<Timezone> getAll() {
        LOGGER.info("Getting all timezones");
        return repository.findAll();
    }

    public Timezone getByName(String name) {
        if (name == null) {
            LOGGER.error("Name cannot be null");
            throw new IllegalArgumentException("Name cannot be null");
        }
        LOGGER.info("Getting timezone by name {}", name);
        return repository.findByName(name)
            .orElseThrow(() -> new RuntimeException("Timezone not found with name " + name));
    }

    public Optional<Timezone> getById(Long id) {
        if (id == null) {
            LOGGER.error("Id cannot be null");
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Getting timezone by id {}", id);
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        if (id == null) {
            LOGGER.error("Id cannot be null");
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Deleting timezone by id {}", id);
        repository.deleteById(id);
    }
}
