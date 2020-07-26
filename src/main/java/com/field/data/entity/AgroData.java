package com.field.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgroData {

    private String dt;
    private Main main;
}
