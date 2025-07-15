package org.example.weatherapplication.repo;

import org.example.weatherapplication.dto.WeatherCache;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherCacheRepository extends JpaRepository<WeatherCache, Long> {
    Optional<WeatherCache> findByPincodeAndDate(String pincode, LocalDate date);
}
