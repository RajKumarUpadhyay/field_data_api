package com.field.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class WeatherData {

    private String timestamp;
    private float temperature;
    private float humidity;
    private float temperatureMin;
    private float temperatureMax;
}
