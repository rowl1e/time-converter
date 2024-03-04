package com.example.timeconverter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.repository.TagRepository;

@Service
public class TagService {
    private final TagRepository repository;

    public TagService(TagRepository repository) {
        this.repository = repository;
    }

    public Tag save(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        return repository.save(tag);
    }

    public List<Tag> getAll() {
        return repository.findAll();
    }

    public List<Tag> getAllByIds(List<Long> ids) {
        if (ids == null) {
            throw new IllegalArgumentException("Ids cannot be null");
        }
        return repository.findAllById(ids);
    }

    public void delete(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        repository.deleteById(id);
    }    

    public Optional<Tag> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        return repository.findById(id);
    }

    public Optional<Tag> getByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        return repository.findByName(name);
    }
} 
