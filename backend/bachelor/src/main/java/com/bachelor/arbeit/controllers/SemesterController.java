package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.SemesterDTO;
import com.bachelor.arbeit.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SemesterController {
    private final SemesterService service;

    @Autowired
    public SemesterController(SemesterService service) {
        this.service = service;
    }

    @GetMapping("study-courses/{id}/semesters")
    public ResponseEntity<List<SemesterDTO>> getSemestersByStudyCourse(@PathVariable Long id){
        List<SemesterDTO> semesters = service.getSemestersByStudyCourse(id);
        if (semesters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(semesters);
        }
        return ResponseEntity.ok(service.getSemestersByStudyCourse(id));
    }

    @PutMapping("/semesters/{id}")
    public ResponseEntity<SemesterDTO> updateNumberOfStudentsBySemesterId(@PathVariable Long id, @RequestBody SemesterDTO dto){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateNumberOfStudentsBySemesterId(id, dto));
    }
}
