package com.example.timeconverter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.timeconverter.exception.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(NumberFormatException e) {
        LOGGER.error("Invalid input. Please enter a number.", e);
        ErrorResponse error = new ErrorResponse("400", "Invalid input. Please enter a number.");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        LOGGER.error("Illegal argument exception", e);
        ErrorResponse error = new ErrorResponse("400", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        LOGGER.error("Internal server error.", e);
        ErrorResponse error = new ErrorResponse("500", "Internal server error.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
