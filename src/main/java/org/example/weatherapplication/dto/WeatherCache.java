package org.example.weatherapplication.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class WeatherCache {
    @Id
    @GeneratedValue
    private Long id;
    private String pincode;
    private LocalDate date;
    private double temp;
    private int humidity, pressure;
    private double windSpeed;
    private String description;
    private LocalDateTime fetchedAt;
}
