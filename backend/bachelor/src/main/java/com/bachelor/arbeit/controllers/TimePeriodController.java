package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.TimePeriodDTO;
import com.bachelor.arbeit.entities.TimePeriod;
import com.bachelor.arbeit.services.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/time-period")
public class TimePeriodController {
    private final TimePeriodService service;

    @Autowired
    public TimePeriodController(TimePeriodService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TimePeriodDTO>> getAllTimePeriods(){
        return ResponseEntity.ok(service.getAllTimePeriods());
    }

    @PostMapping
    public ResponseEntity<TimePeriodDTO> createTimePeriod(@RequestBody TimePeriodDTO timePeriod){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createTimePeriod(timePeriod));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimePeriodDTO> updateTimePeriod(@PathVariable Long id, @RequestBody TimePeriodDTO timePeriod){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateTimePeriod(id, timePeriod));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimePeriod(@PathVariable Long id){
        service.deleteTimePeriod(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
