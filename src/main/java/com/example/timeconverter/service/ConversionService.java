package com.example.timeconverter.service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.timeconverter.entity.Conversion;
import com.example.timeconverter.entity.Timezone;
import com.example.timeconverter.entity.Tag;
import com.example.timeconverter.model.ConversionRequest;
import com.example.timeconverter.repository.ConversionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ConversionService {
    private final ConversionRepository repository;
    private final TimezoneService timezoneService;
    private final CacheService cacheService; // Добавить CacheService как зависимость

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionService.class);

    public ConversionService(ConversionRepository repository, TimezoneService timezoneService, CacheService cacheService) { // Внедрить CacheService через конструктор
        this.repository = repository;
        this.timezoneService = timezoneService;
        this.cacheService = cacheService;
    }

    public Conversion save(Conversion conversion) {
        if (conversion == null) {
            throw new IllegalArgumentException("Conversion cannot be null");
        }
        LOGGER.info("Saving conversion");
        cacheService.clear();
        return repository.save(conversion);
    }

    public List<Conversion> getAll() {
        LOGGER.info("Getting all conversions");
        return repository.findAll();
    } 

    public Optional<Conversion> getById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Getting conversion by id");
        return repository.findById(id);
    }

    public List<Conversion> getAllByTag(Tag tag) {
        if (tag == null) {
            throw new IllegalArgumentException("Tag cannot be null");
        }
        LOGGER.info("Getting all conversions by tag");
        return repository.findAllByTagsContains(tag);
    }

    public void deleteById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        LOGGER.info("Deleting conversion by id");
        repository.deleteById(id);
        cacheService.clear();
    }    

    public Conversion convertTime(ConversionRequest request, Conversion conversion) {
        Long timeInSeconds = request.getTimeInSeconds();
        String timezoneName = request.getTimezoneName();

        if (timeInSeconds == null || timeInSeconds < 0) {
            throw new IllegalArgumentException("Invalid input. Please enter a positive number.");
        }

        // Получить timezone из базы данных
        Timezone timezone = timezoneService.getByName(timezoneName);

        // Если timezoneName равно "currentTimezone", использовать системный часовой пояс
        ZoneId zoneId;
        if ("currentTimezone".equals(timezoneName)) {
            zoneId = ZoneId.systemDefault();
        } else {
            zoneId = ZoneId.of(timezone.getName());
        }

        // Convert timestamp to the specified timezone
        Instant instant = Instant.ofEpochSecond(timeInSeconds);
        ZonedDateTime specifiedTimezone = instant.atZone(zoneId);

        // Форматировать время
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss VV");
        String specifiedTimezoneStr = specifiedTimezone.format(formatter);

        // Обновить поля conversion
        conversion.setTimeInSeconds(timeInSeconds);
        conversion.setSpecifiedTimezone(specifiedTimezoneStr);
        conversion.setConversionDate(ZonedDateTime.now());
        conversion.setTimezone(timezone);

        LOGGER.info("Converting time for request");
        return conversion;
    }

    public List<Conversion> getByTimezoneName(String timezoneName) {
        List<Conversion> data = cacheService.getFromCache(timezoneName);
        if (data == null) {
            LOGGER.info("Fetching data from the database...");
            data = repository.findByTimezoneName(timezoneName);
            cacheService.putInCache(timezoneName, data);
        } else {
            LOGGER.info("Fetching data from the cache...");
        }
        return data;
    }
}
