package com.example.timeconverter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ConversionType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "conversionType")
    @JsonIgnore  // Игнорировать это свойство при сериализации
    private List<ConversionHistory> conversionHistories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ConversionHistory> getConversionHistories() {
        return conversionHistories;
    }

    public void setConversionHistories(List<ConversionHistory> conversionHistories) {
        this.conversionHistories = conversionHistories;
    }
}
