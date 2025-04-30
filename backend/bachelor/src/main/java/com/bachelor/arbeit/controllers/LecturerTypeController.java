package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.LecturerDTO;
import com.bachelor.arbeit.dtos.LecturerTypeDTO;
import com.bachelor.arbeit.services.LecturerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/lecturer-type")
public class LecturerTypeController {
    private final LecturerTypeService service;

    @Autowired
    public LecturerTypeController(LecturerTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LecturerTypeDTO>> getLecturerTypes() {
        return ResponseEntity.ok(service.getLecturerTypes());
    }

    @Transactional
    @PostMapping
    public ResponseEntity<LecturerTypeDTO> createLecturerType(@RequestBody LecturerTypeDTO lecturerTypeDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createLecturerType(lecturerTypeDTO));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<LecturerTypeDTO> updateLecturerType(@PathVariable Long id,
                                                              @RequestBody LecturerTypeDTO lecturerTypeDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateLecturerType(id, lecturerTypeDTO));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLecturerType(@PathVariable Long id) {
        boolean isDeleted = service.deleteLecturerType(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
