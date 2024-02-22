package com.example.timeconverter.service;

import org.springframework.stereotype.Service;
import com.example.timeconverter.model.TimeResponse;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class TimeConverterService {

    public TimeResponse convertTime(long timeInSeconds) {
        if (timeInSeconds < 0) {
            throw new IllegalArgumentException("Invalid input. Please enter a positive number.");
        }

        Instant instant = Instant.ofEpochSecond(timeInSeconds);

        ZonedDateTime currentZone = instant.atZone(ZoneId.systemDefault());
        ZonedDateTime gmtZone = instant.atZone(ZoneId.of("GMT"));

        return new TimeResponse(currentZone.toString(), gmtZone.toString());
    }
}
