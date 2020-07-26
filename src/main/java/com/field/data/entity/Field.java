package com.field.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "FIELD")
@Entity
@Data
@ToString
public class Field  implements Serializable {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "CREATED", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "UPDATED")
    private LocalDateTime updated;

    @Column(name = "COUNTRY_CODE", length = 5, nullable = false)
    private String countryCode;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "field")
    @JsonIgnoreProperties("field")
    private Boundaries boundaries;
}
