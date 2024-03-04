package com.example.timeconverter.repository;

import com.example.timeconverter.entity.ConversionHistory;
import com.example.timeconverter.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {
    List<ConversionHistory> findAllByTagsContains(Tag tag);
}
