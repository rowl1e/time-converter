package com.example.timeconverter.controller;

import com.example.timeconverter.entity.Conversion;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.entity.Timezone;
import com.example.timeconverter.model.ConversionRequest;
import com.example.timeconverter.service.ConversionService;
import com.example.timeconverter.service.TimezoneService;
import com.example.timeconverter.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConversionController {
    private final ConversionService conversionService;
    private final TagService tagService;
    private final TimezoneService timezoneService;

    private static final String CONVERSION_HISTORY_NOT_FOUND = "Conversion not found with id ";
    private static final String NOT_FOUND = "not found";

    public ConversionController(ConversionService conversionService, TagService tagService, TimezoneService timezoneService) {
        this.conversionService = conversionService;
        this.tagService = tagService;
        this.timezoneService = timezoneService;
    }

    @PostMapping("/conversion")
    public Conversion createConversion(@RequestBody ConversionRequest request) {
        Conversion conversion = new Conversion();
        
        // Если в запросе не указано время или указано значение 0, установить значение по умолчанию
        Long timeInSeconds = (request.getTimeInSeconds() != null) ? request.getTimeInSeconds() : 0L;
        // Если в запросе не указан часовой пояс или указано пустое значение, установить значение по умолчанию
        String timezoneName = (request.getTimezoneName() != null && !request.getTimezoneName().isEmpty()) ? request.getTimezoneName() : "GMT";
        
        // Проверить, существует ли часовой пояс
        Timezone timezone = timezoneService.getByName(timezoneName);
        if (timezone == null) {
            throw new IllegalArgumentException("Timezone" + NOT_FOUND);
        }
        
        request.setTimeInSeconds(timeInSeconds);
        request.setTimezoneName(timezoneName);
        
        return conversionService.save(conversionService.convertTime(request, conversion));
    }

    @GetMapping("/conversion")
    public List<Conversion> getAll() {
        return conversionService.getAll();
    }

    @PutMapping("/conversion/{id}")
    public Conversion updateConversion(@PathVariable Long id, @RequestBody ConversionRequest request) {
        Conversion conversion = conversionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException(CONVERSION_HISTORY_NOT_FOUND + id));
            
        // Если в запросе не указано время, использовать текущее значение
        Long timeInSeconds = (request.getTimeInSeconds() != null) ? request.getTimeInSeconds() : conversion.getTimeInSeconds();
        // Если в запросе не указан часовой пояс, использовать текущее значение
        String timezoneName = (request.getTimezoneName() != null && !request.getTimezoneName().isEmpty()) ? request.getTimezoneName() : conversion.getTimezone().getName();
        
        request.setTimeInSeconds(timeInSeconds);
        request.setTimezoneName(timezoneName);
        
        return conversionService.save(conversionService.convertTime(request, conversion));
    }

    @DeleteMapping("/conversion/{id}")
    public void deleteConversion(@PathVariable Long id) {
        conversionService.deleteById(id);
    }

    @PatchMapping("/conversion/{id}/tag/{tagId}")
    public Conversion addTagToConversion(@PathVariable Long id, @PathVariable Long tagId) {
        Conversion conversion = conversionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException(CONVERSION_HISTORY_NOT_FOUND + id));

        Tag tag = tagService.getById(tagId)
            .orElseThrow(() -> new IllegalArgumentException("Tag" + NOT_FOUND));

        // Check if the tag is already associated with the conversion
        if (conversion.getTags().contains(tag)) {
            throw new IllegalArgumentException("This tag is already associated with this conversion");
        }

        // Add the tag to Conversion
        conversion.getTags().add(tag);

        return conversionService.save(conversion);
    }

    @GetMapping("/conversion/tag/{tagName}")
    public List<Conversion> getAllByTag(@PathVariable String tagName) {
        Tag tag = tagService.getByName(tagName)
            .orElseThrow(() -> new RuntimeException("Tag" + NOT_FOUND));

        return conversionService.getAllByTag(tag);
    }

    @GetMapping("/conversion/timezone/{timezoneName}")
    public List<Conversion> getByTimezoneName(@PathVariable String timezoneName) {
        return conversionService.getByTimezoneName(timezoneName);
    }

    @GetMapping("/conversion/{id}")
    public Conversion getById(@PathVariable Long id) {
        return conversionService.getById(id)
            .orElseThrow(() -> new IllegalArgumentException(CONVERSION_HISTORY_NOT_FOUND + id));
    }
}
