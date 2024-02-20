package com.example.timeconverter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.timeconverter.service.TimeConverterService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TimeConverterController {

    private final TimeConverterService timeConverterService;

    public TimeConverterController(TimeConverterService timeConverterService) {
        this.timeConverterService = timeConverterService;
    }

    @GetMapping("/convert")
    public ResponseEntity<Map<String, String>> convertTime(@RequestParam long timeInSeconds) {
        return ResponseEntity.ok(timeConverterService.convertTime(timeInSeconds));
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(NumberFormatException e) {
        Map<String, String> error = new HashMap<>();
        error.put("status", "400");
        error.put("error", "Invalid input. Please enter a number.");
        return ResponseEntity.badRequest().body(error);
    }
}
