package com.field.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {
    private float temp;
    private float humidity;
    private float temp_min;
    private float temp_max;
}
