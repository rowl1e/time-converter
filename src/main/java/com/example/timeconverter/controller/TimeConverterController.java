package com.example.timeconverter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.timeconverter.service.TimeConverterService;
import com.example.timeconverter.model.TimeResponse;

@RestController
public class TimeConverterController {

    private final TimeConverterService timeConverterService;

    public TimeConverterController(TimeConverterService timeConverterService) {
        this.timeConverterService = timeConverterService;
    }

    @GetMapping("/convert")
    public ResponseEntity<TimeResponse> convertTime(@RequestParam(defaultValue = "0") long timeInSeconds) {
        return ResponseEntity.ok(timeConverterService.convertTime(timeInSeconds));
    }
}
