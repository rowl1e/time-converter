package com.example.timeconverter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long timeInSeconds;
    private String specifiedTimezone; 
    private ZonedDateTime conversionDate;

    @ManyToOne
    private ConversionType conversionType;

    @ManyToMany
    private List<Tag> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeInSeconds() {
        return timeInSeconds;
    }

    public void setTimeInSeconds(Long timeInSeconds) {
        this.timeInSeconds = timeInSeconds;
    }

    public String getSpecifiedTimezone() {
        return specifiedTimezone;
    }

    public void setSpecifiedTimezone(String specifiedTimezone) {
        this.specifiedTimezone = specifiedTimezone;
    }

    public ZonedDateTime getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(ZonedDateTime conversionDate) {
        this.conversionDate = conversionDate;
    }

    public ConversionType getConversionType() {
        return conversionType;
    }

    public void setConversionType(ConversionType conversionType) {
        this.conversionType = conversionType;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}

