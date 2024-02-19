package com.example.timeconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TimeConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeConverterApplication.class, args);
    }

}

@RestController
class TimeConverterController {

    @GetMapping("/convert")
    public Map<String, String> convertTime(@RequestParam long timeInSeconds) {
        // Преобразование секунд в миллисекунды
        long timeInMillis = timeInSeconds * 1000;

        Instant instant = Instant.ofEpochMilli(timeInMillis);

        ZonedDateTime currentZone = instant.atZone(ZoneId.systemDefault());
        ZonedDateTime gmtZone = instant.atZone(ZoneId.of("GMT"));

        Map<String, String> result = new HashMap<>();
        result.put("currentTimezone", currentZone.toString());
        result.put("GMT", gmtZone.toString());

        return result;
    }

}
