package com.field.data.api;

import com.field.data.entity.Field;
import com.field.data.entity.Weather;
import com.field.data.service.FieldDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(path = "/fields")
public class FieldDataController {

    @Autowired
    FieldDataService fieldDataService;

    @GetMapping
    public ResponseEntity<List<Field>> getAllFieldsData() {
        return new ResponseEntity<>(fieldDataService.getAllFieldData(), HttpStatus.OK);
    }

    @GetMapping(path = "/{fieldId}/weather")
    public ResponseEntity<List<Weather>> getWeatherHistory(@PathVariable("fieldId") String fieldId) {
        return new ResponseEntity<>(fieldDataService.getWeatherHistoryData(fieldId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus>  saveFieldData(@RequestBody Field fieldData ) {
        fieldDataService.saveData(fieldData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/{fieldId}")
    public ResponseEntity<Field>  updateFieldData(@RequestBody Field fieldData, @PathVariable("fieldId") UUID fieldId) {
        return new ResponseEntity(fieldDataService.updateData(fieldData, fieldId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{fieldId}")
    public ResponseEntity<HttpStatus>  updateFieldData(@PathVariable("fieldId") UUID fieldId) {
        fieldDataService.deleteData(fieldId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
