package com.field.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Embeddable
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GeoJson {
    private String type;
    private Properties properties;
    @Embedded
    @AttributeOverrides(value = {
           // @AttributeOverride( name = "type", column = @Column(name = "geometry_polygon_type")),
            @AttributeOverride( name = "geometry", column = @Column(name = "geometry", columnDefinition = "geometry"))
    })
    private Geometry geometry;
}
