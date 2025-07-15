package org.example.weatherapplication.service;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link org.example.weatherapplication.dto.WeatherDto}
 */
@Value
public class WeatherApiResponse implements Serializable {
    String pincode;
    double lat;
    double lon;
    LocalDate date;
    double temperature;
    int humidity;
    int pressure;
    double windSpeed;
    String description;
}