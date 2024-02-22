package com.example.timeconverter.model;

public class TimeResponse {

    private String currentTimezone;
    private String GMT;

    public TimeResponse(String currentTimezone, String GMT) {
        this.currentTimezone = currentTimezone;
        this.GMT = GMT;
    }

    public String getCurrentTimezone() {
        return currentTimezone;
    }

    public void setCurrentTimezone(String currentTimezone) {
        this.currentTimezone = currentTimezone;
    }

    public String getGMT() {
        return GMT;
    }

    public void setGMT(String GMT) {
        this.GMT = GMT;
    }
}
