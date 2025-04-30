package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.StudyCourseDTO;
import com.bachelor.arbeit.services.StudyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/study-course")
public class StudyCourseController {
    private final StudyCourseService service;

    @Autowired
    public StudyCourseController(StudyCourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<StudyCourseDTO>> getStudyCourses() {
        return ResponseEntity.ok(service.getStudyCourses());
    }

    @PostMapping
    public ResponseEntity<StudyCourseDTO> createStudyCourse(@RequestBody StudyCourseDTO studyCourseDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createStudyCourse(studyCourseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudyCourseDTO> updateStudyCourse(@PathVariable Long id, @RequestBody StudyCourseDTO studyCourseDTO) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateStudyCourse(id, studyCourseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudyCourse(@PathVariable Long id) {
        boolean isDeleted = service.deleteStudyCourse(id);
        if(isDeleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
