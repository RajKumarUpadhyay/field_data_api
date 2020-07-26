package com.field.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class Weather {

    private List<WeatherData> weather;
}
