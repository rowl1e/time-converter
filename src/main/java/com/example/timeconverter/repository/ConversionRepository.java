package com.example.timeconverter.repository;

import com.example.timeconverter.entity.Conversion;
import com.example.timeconverter.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    List<Conversion> findAllByTagsContains(Tag tag);
    
    @Query("SELECT c FROM Conversion c WHERE c.timezone.name = :timezoneName")
    List<Conversion> findByTimezoneName(@Param("timezoneName") String timezoneName);
}
