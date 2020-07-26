package com.field.data.service;

import com.field.data.entity.Boundaries;
import com.field.data.entity.Field;
import com.field.data.entity.GeoJson;
import com.field.data.repository.FieldDataRepository;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
class FieldDataServiceTest {

    @InjectMocks
    private FieldDataService fieldDataService;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    FieldDataRepository fieldDataRepository;

    private Field field;
    private Boundaries boundaries;
    private UUID fieldId;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

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
        System.out.println("cleanUpEach() method called");
    }

    @Test
    void getAllFieldData() {
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(field);

        when(fieldDataRepository.findAll()).thenReturn(fieldList);
        when(fieldDataService.getAllFieldData()).thenReturn(fieldList);

        List<Field> empList = fieldDataService.getAllFieldData();
        assertEquals(1, empList.size());
    }

    @Test
    void updateData() {
        field.setBoundaries(field.getBoundaries());
        when(fieldDataRepository.findFieldById(fieldId)).thenReturn(java.util.Optional.ofNullable(field));
        when(fieldDataService.updateData(field, fieldId)).thenReturn(java.util.Optional.ofNullable(field));
        Optional<Field> fieldNew = fieldDataService.updateData(field, fieldId);
        assertTrue(fieldNew.isPresent());
        assertEquals(field.getId(), fieldNew.get().getId());
    }

    @Test
    void deleteData() {
        when(fieldDataRepository.findFieldById(fieldId)).thenReturn(java.util.Optional.ofNullable(field));
        doNothing().when(fieldDataRepository).deleteById(fieldId);
        fieldDataService.deleteData(fieldId);
    }
}