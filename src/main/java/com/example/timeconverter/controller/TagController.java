package com.example.timeconverter.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.service.TagService;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    private final TagService service;

    public TagController(TagService service) {
        this.service = service;
    }

    @PostMapping
    public Tag create(@RequestBody Tag tag) {
        return service.save(tag);
    }

    @GetMapping
    public List<Tag> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable Long id, @RequestBody Tag updatedTag) {
        Tag tag = service.getById(id)
            .orElseThrow(() -> new RuntimeException("Tag not found with id " + id));

        // Обновить поля tag на основе updatedTag
        tag.setName(updatedTag.getName());

        return service.save(tag);
    }
}
