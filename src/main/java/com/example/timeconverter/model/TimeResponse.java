package com.example.timeconverter.model;

public class TimeResponse {

    private String currentTimezone;
    private String gmt;

    public TimeResponse(String currentTimezone, String gmt) {
        this.currentTimezone = currentTimezone;
        this.gmt = gmt;
    }

    public String getCurrentTimezone() {
        return currentTimezone;
    }

    public void setCurrentTimezone(String currentTimezone) {
        this.currentTimezone = currentTimezone;
    }

    public String getGmt() {
        return gmt;
    }

    public void setGmt(String gmt) {
        this.gmt = gmt;
    }
}
