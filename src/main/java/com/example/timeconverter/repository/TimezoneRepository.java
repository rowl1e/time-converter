package com.example.timeconverter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.timeconverter.entity.Timezone;
import java.util.Optional;

public interface TimezoneRepository extends JpaRepository<Timezone, Long> {
    Optional<Timezone> findByName(String name);
}
