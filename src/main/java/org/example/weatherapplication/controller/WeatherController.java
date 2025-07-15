package org.example.weatherapplication.controller;

import org.example.weatherapplication.dto.WeatherDto;
import org.example.weatherapplication.service.WeatherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService svc;
    public WeatherController(WeatherService svc){ this.svc=svc; }

    @GetMapping
    public WeatherDto getWeather(
            @RequestParam String pincode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate for_date) {
        return svc.getWeather(pincode, for_date);
    }
}
