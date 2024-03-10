package com.example.timeconverter.model;

public class ConversionRequest {
    private Long timeInSeconds;
    private String timezoneName;
    
    public Long getTimeInSeconds() {
        return timeInSeconds;
    }
    public void setTimeInSeconds(Long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }
    public String getTimezoneName() {
        return timezoneName;
    }
    public void setTimezoneName(String timezoneName) {
        this.timezoneName = timezoneName;
    }
}
