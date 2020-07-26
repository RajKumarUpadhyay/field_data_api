package com.field.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private String type;
   /* @ElementCollection(targetClass = Double.class)
    private List<List<List<Double>>> coordinates;*/
}
