package com.example.timeconverter.controller;

import com.example.timeconverter.entity.ConversionHistory;
import com.example.timeconverter.entity.ConversionType;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.service.ConversionHistoryService;
import com.example.timeconverter.service.ConversionTypeService;
import com.example.timeconverter.service.TagService;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class ConversionHistoryController {
    private final ConversionHistoryService historyService;
    private final ConversionTypeService typeService;
    private final TagService tagService;

    private static final String CONVERSION_HISTORY_NOT_FOUND = "ConversionHistory not found with id ";

    public ConversionHistoryController(ConversionHistoryService historyService, ConversionTypeService typeService, TagService tagService) {
        this.historyService = historyService;
        this.typeService = typeService;
        this.tagService = tagService;
    }

    @GetMapping("/convert")
    public ConversionHistory convert(@RequestParam Long timeInSeconds, @RequestParam String conversionTypeName) {
        if (timeInSeconds == null || timeInSeconds < 0) {
            throw new IllegalArgumentException("Invalid input. Please enter a positive number.");
        }

        // Получить ConversionType из базы данных
        ConversionType conversionType = typeService.getByName(conversionTypeName);

        // Если conversionTypeName равно "currentTimezone", используйте системный часовой пояс
        ZoneId zoneId;
        if ("currentTimezone".equals(conversionTypeName)) {
            zoneId = ZoneId.systemDefault();
        } else {
            zoneId = ZoneId.of(conversionType.getName());
        }

        // Convert timestamp to the specified timezone
        Instant instant = Instant.ofEpochSecond(timeInSeconds);
        ZonedDateTime specifiedTimezone = instant.atZone(zoneId);

        // Format the time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");
        String specifiedTimezoneStr = specifiedTimezone.format(formatter);

        // Save conversion history
        ConversionHistory conversionHistory = new ConversionHistory();
        conversionHistory.setTimeInSeconds(timeInSeconds);
        conversionHistory.setSpecifiedTimezone(specifiedTimezoneStr);
        conversionHistory.setConversionDate(ZonedDateTime.now());
        conversionHistory.setConversionType(conversionType); // Set the ConversionType

        return historyService.save(conversionHistory);
    }

    @GetMapping("/conversion")
    public List<ConversionHistory> getAll() {
        return historyService.getAll();
    }

    @PostMapping("/conversion/{id}/tags")
    public ConversionHistory addTagToConversion(@PathVariable Long id, @RequestBody Tag tag) {
        // Получить ConversionHistory из базы данных
        ConversionHistory conversionHistory = historyService.getById(id)
            .orElseThrow(() -> new RuntimeException(CONVERSION_HISTORY_NOT_FOUND + id));

        // Добавить тег к ConversionHistory
        conversionHistory.getTags().add(tag);

        // Сохранить ConversionHistory
        return historyService.save(conversionHistory);
    }

    @GetMapping("/conversion/tag/{tagName}")
    public List<ConversionHistory> getAllByTag(@PathVariable String tagName) {
        // Получить тег из базы данных
        Tag tag = tagService.getByName(tagName)
            .orElseThrow(() -> new RuntimeException("Tag not found with name " + tagName));

        // Получить все записи истории конвертации с этим тегом
        return historyService.getAllByTag(tag);
    }

    @GetMapping("/conversion/{id}")
    public ConversionHistory getById(@PathVariable Long id) {
        return historyService.getById(id)
            .orElseThrow(() -> new RuntimeException(CONVERSION_HISTORY_NOT_FOUND + id));
    }

    @PutMapping("/conversion/{id}")
    public ConversionHistory updateConversion(@PathVariable Long id, @RequestBody ConversionHistory updatedConversion) {
        ConversionHistory conversionHistory = historyService.getById(id)
            .orElseThrow(() -> new RuntimeException(CONVERSION_HISTORY_NOT_FOUND + id));

        // Обновить поля conversionHistory на основе updatedConversion
        conversionHistory.setTimeInSeconds(updatedConversion.getTimeInSeconds());
        conversionHistory.setSpecifiedTimezone(updatedConversion.getSpecifiedTimezone());
        conversionHistory.setConversionDate(updatedConversion.getConversionDate());
        conversionHistory.setConversionType(updatedConversion.getConversionType());
        conversionHistory.setTags(updatedConversion.getTags());

        return historyService.save(conversionHistory);
    }

    @DeleteMapping("/conversion/{id}")
    public void deleteConversion(@PathVariable Long id) {
        historyService.deleteById(id);
    }
}
