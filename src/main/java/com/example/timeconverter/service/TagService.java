package com.example.timeconverter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.repository.TagRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class TagService {
    private final TagRepository repository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TagService.class);

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Tag save(Tag tag) {
        if (tag == null) {
            LOGGER.error("Attempted to save null tag");
            throw new IllegalArgumentException("Tag cannot be null");
        }
        LOGGER.info("Saving tag {}", tag);
        return repository.save(tag);
    }

    public List<Tag> getAll() {
        LOGGER.info("Getting all tags");
        return repository.findAll();
    }

    public List<Tag> getAllByIds(List<Long> ids) {
        if (ids == null) {
            LOGGER.error("Ids cannot be null");
            throw new IllegalArgumentException("Ids cannot be null");
        }
        LOGGER.info("Getting all tags by ids {}", ids);
        return repository.findAllById(ids);
    }

    public void delete(Long id) {
        if (id == null) {
            LOGGER.error("Id cannot be null");
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Deleting tag by id {}", id);
        repository.deleteById(id);
    }    

    public Optional<Tag> getById(Long id) {
        if (id == null) {
            LOGGER.error("Id cannot be null");
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Getting tag by id {}", id);
        return repository.findById(id);
    }

    public Optional<Tag> getByName(String name) {
        if (name == null) {
            LOGGER.error("Name cannot be null");
            throw new IllegalArgumentException("Name cannot be null");
        }
        LOGGER.info("Getting tag by name {}", name);
        return repository.findByName(name);
    }
}
