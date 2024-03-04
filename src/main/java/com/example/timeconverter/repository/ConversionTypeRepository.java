package com.example.timeconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.timeconverter.entity.ConversionType;
import java.util.Optional;

public interface ConversionTypeRepository extends JpaRepository<ConversionType, Long> {
    Optional<ConversionType> findByName(String name);
}
