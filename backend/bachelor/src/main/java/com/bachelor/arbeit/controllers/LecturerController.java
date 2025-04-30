package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.LecturerDTO;
import com.bachelor.arbeit.services.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/lecturer")
public class LecturerController {
    private final LecturerService service;

    @Autowired
    public LecturerController(LecturerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LecturerDTO>> getLecturers() {
        return ResponseEntity.ok(service.getLecturers());
    }

    @PostMapping
    public ResponseEntity<LecturerDTO> createLecturer(@RequestBody LecturerDTO lecturerDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLecturer(lecturerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LecturerDTO> updateLecturer(@PathVariable Long id, @RequestBody LecturerDTO lecturerDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateLecturer(id, lecturerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturer(@PathVariable Long id) {
        boolean isDeleted = service.deleteLecturer(id);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
