package org.example.weatherapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class WeatherDto {
    private String pincode;
    private double lat, lon;
    private LocalDate date;
    private double temperature;
    private int humidity, pressure;
    private double windSpeed;
    private String description;

}
