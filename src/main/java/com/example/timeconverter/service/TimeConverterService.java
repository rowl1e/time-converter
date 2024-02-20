package com.example.timeconverter.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class TimeConverterService {

    public Map<String, String> convertTime(long timeInSeconds) {
        if (timeInSeconds < 0) {
            throw new IllegalArgumentException("Invalid input. Please enter a positive number.");
        }

        Instant instant = Instant.ofEpochSecond(timeInSeconds);

        ZonedDateTime currentZone = instant.atZone(ZoneId.systemDefault());
        ZonedDateTime gmtZone = instant.atZone(ZoneId.of("GMT"));

        Map<String, String> result = new HashMap<>();
        result.put("currentTimezone", currentZone.toString());
        result.put("GMT", gmtZone.toString());

        return result;
    }

}
