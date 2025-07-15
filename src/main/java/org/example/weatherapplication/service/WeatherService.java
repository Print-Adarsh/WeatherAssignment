package org.example.weatherapplication.service;

import org.example.weatherapplication.dto.WeatherCache;
import org.example.weatherapplication.dto.WeatherDto;
import org.example.weatherapplication.entities.ZipcodeMap;
import org.example.weatherapplication.repo.WeatherCacheRepository;
import org.example.weatherapplication.repo.ZipcodeMapRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import java.time.LocalDate;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class WeatherService {

    @Value("${openweather.apikey}") private String apiKey;
    private final ZipcodeMapRepository zipRepo;
    private final WeatherCacheRepository cacheRepo;
    private final RestTemplate rt;

    public WeatherService(ZipcodeMapRepository z, WeatherCacheRepository c, RestTemplate rt){
        this.zipRepo=z; this.cacheRepo=c; this.rt=rt;
    }

    @Transactional
    public WeatherDto getWeather(String pincode, LocalDate date){
        var zip=zipRepo.findById(pincode)
                .filter(z->z.getUpdatedAt().isAfter(LocalDateTime.now().minusDays(1)))
                .orElseGet(()-> fetchAndSaveCoords(pincode));

        var cache=cacheRepo.findByPincodeAndDate(pincode, date);
        if(cache.isPresent()){
            return toDto(zip.getLat(), zip.getLon(), cache.get());
        }

        var wc = fetchWeather(zip.getLat(), zip.getLon(), date);
        wc.setPincode(pincode); wc.setDate(date);
        cacheRepo.save(wc);
        return toDto(zip.getLat(), zip.getLon(), wc);
    }

    private ZipcodeMap fetchAndSaveCoords(String pincode){
        var resp=rt.getForObject("http://api.openweathermap.org/geo/1.0/zip?zip="+pincode+",IN&appid="+apiKey, GeoResponse.class);
        ZipcodeMap zm=new ZipcodeMap();
        zm.setPincode(pincode); zm.setLat(resp.getLat()); zm.setLon(resp.getLon());
        zm.setUpdatedAt(LocalDateTime.now());
        return zipRepo.save(zm);
    }

    private WeatherCache fetchWeather(double lat, double lon, LocalDate date){
        // call weather API (current or historical)
        var url= "http://api.openweathermap.org/data/2.5/weather?...";
        var resp=rt.getForObject(url, WeatherApiResponse.class);
        WeatherCache wc = new WeatherCache();
        wc.setTemp(resp.getMain().getTemp());
        // set other fields similarly
        wc.setFetchedAt(LocalDateTime.now());
        return wc;
    }

    private WeatherDto toDto(double lat, double lon, WeatherCache wc){
        WeatherDto d=new WeatherDto();
        d.setPincode(wc.getPincode()); d.setLat(lat); d.setLon(lon);
        d.setDate(wc.getDate()); d.setTemperature(wc.getTemp());
        d.setHumidity(wc.getHumidity()); d.setPressure(wc.getPressure());
        d.setWindSpeed(wc.getWindSpeed()); d.setDescription(wc.getDescription());
        return d;
    }
}
