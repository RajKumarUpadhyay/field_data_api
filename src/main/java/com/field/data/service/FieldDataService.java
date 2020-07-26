package com.field.data.service;

import com.field.data.entity.*;
import com.field.data.exception.ResourceNotFoundException;
import com.field.data.repository.FieldDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FieldDataService {

    @Autowired
    FieldDataRepository fieldDataRepository;

    @Autowired
    RestTemplate restTemplate;

    @Value("${api-key}")
    private String appid;

    @Value("${rest-end-point}")
    private String apiEndPoint;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        // Do any additional configuration here
        return builder.build();
    }

    public List<Field> getAllFieldData() {
        List<Field> fieldList = fieldDataRepository.findAll();
        fieldList.stream().findAny().orElseThrow(() -> new ResourceNotFoundException("No Record Found!"));
        return fieldList;
    }

    public List<Weather> getWeatherHistoryData(String fieldId) {
        Long start = LocalDateTime.now().minusDays(7L).atZone(ZoneId.systemDefault()).toEpochSecond();
        Long end = LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();

        String url = apiEndPoint+"polyid="+fieldId+"&appid="+appid+"&start="+start+"&end="+end;

        AgroData[]  weatherAPiData = restTemplate.getForObject(url, AgroData[] .class);

        List<WeatherData> weatherData = new ArrayList<>();
        WeatherData parsedWeatherApiData ;
        for (AgroData agroData : weatherAPiData) {
            parsedWeatherApiData = new WeatherData();
            parsedWeatherApiData.setTimestamp(agroData.getDt());
            parsedWeatherApiData.setHumidity(agroData.getMain().getHumidity());
            parsedWeatherApiData.setTemperature(agroData.getMain().getTemp());
            parsedWeatherApiData.setTemperatureMax(agroData.getMain().getTemp_max());
            parsedWeatherApiData.setTemperatureMin(agroData.getMain().getTemp_min());
            weatherData.add(parsedWeatherApiData);
        }

        List<Weather> weatherArrayList = new ArrayList<>();
        Weather weather = new Weather();
        weather.setWeather(weatherData);
        weatherArrayList.add(weather);

        return  weatherArrayList;
    }

    public void saveData(Field fieldData) {
        fieldData.getBoundaries().setField(fieldData);
        fieldDataRepository.save(fieldData);
    }

    public Optional<Field> updateData(Field fieldData, UUID fieldId) {

        fieldDataRepository.findFieldById(fieldId).ifPresentOrElse(field -> {
            field.setName(fieldData.getName());
            field.setCreated(fieldData.getCreated());
            field.setUpdated(LocalDateTime.now());
            field.getBoundaries().setUpdated(field.getUpdated());
            field.setCountryCode(fieldData.getCountryCode());
            fieldDataRepository.save(field);
        }, () -> {
            throw new ResourceNotFoundException("Provided Field ID Not Exist "+fieldId);
        });
        return fieldDataRepository.findFieldById(fieldId);
    }

    public void deleteData(UUID fieldId) {

        fieldDataRepository.findFieldById(fieldId).ifPresentOrElse(field -> {
            fieldDataRepository.deleteById(fieldId);
        }, () -> {
            throw new ResourceNotFoundException("Provided Field ID Not Exist "+fieldId);
        });
    }
}

