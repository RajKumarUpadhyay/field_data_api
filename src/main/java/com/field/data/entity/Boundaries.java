package com.field.data.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "BOUNDARY")
@Entity
@Data
@ToString
public class Boundaries implements Serializable {

    @Id
    private UUID id;

    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @Column(name = "UPDATED")
    private LocalDateTime updated;

    @Embedded
    @AttributeOverrides(value = {
            @AttributeOverride( name = "type", column = @Column(name = "geojson_type")),
            @AttributeOverride( name = "properties", column = @Column(name = "geojson_properties")),
           // @AttributeOverride( name = "geometry", column = @Column(name = "geojson_geometry")),
    })
    private GeoJson geoJson;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    private Field field;
}