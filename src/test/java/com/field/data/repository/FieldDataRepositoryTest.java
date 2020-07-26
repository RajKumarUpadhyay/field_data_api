package com.field.data.repository;

import com.field.data.entity.Boundaries;
import com.field.data.entity.Field;
import com.field.data.entity.GeoJson;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
class FieldDataRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private FieldDataRepository fieldDataRepository;
    private Field field;
    private Boundaries boundaries;
    private UUID fieldId;

    @BeforeEach
    void setUp() {
        fieldId = UUID.randomUUID();
        field = new Field();
        boundaries = new Boundaries();
        field.setId(fieldId);
        field.setName("Apple Field");
        field.setCountryCode("DE");
        field.setCreated(LocalDateTime.now());
        field.setBoundaries(boundaries);
        boundaries.setField(field);
        boundaries.setCreated(field.getCreated());
        boundaries.setId(field.getId());
        GeoJson geoJson = new GeoJson();
        boundaries.setGeoJson(geoJson);
    }

    @AfterEach
    void tearDown() {
    }

    @Test

    void findAll() {
        entityManager.merge(field);
        List<Field> fieldList = fieldDataRepository.findAll();
        assertEquals(1, fieldList.size());
    }

    @Test
    void findFieldById() {
        entityManager.merge(field);
        Optional<Field> fieldFromDB = fieldDataRepository.findFieldById(fieldId);
        assertTrue(fieldFromDB.isEmpty());
    }
}