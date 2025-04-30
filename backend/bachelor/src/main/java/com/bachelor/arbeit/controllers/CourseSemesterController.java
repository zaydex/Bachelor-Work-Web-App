package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.CourseSemesterDTO;
import com.bachelor.arbeit.services.CourseSemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/course-semester")
public class CourseSemesterController {

    private final CourseSemesterService service;

    @Autowired
    public CourseSemesterController(CourseSemesterService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseSemesterDTO>> getCourseSemesters(){
        return ResponseEntity.ok(service.getCourseSemesters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseSemesterDTO> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getCourseSemesterById(id));
    }

    @PostMapping
    public ResponseEntity<CourseSemesterDTO> createCourseSemester(@RequestBody CourseSemesterDTO courseSemesterDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(courseSemesterDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseSemesterDTO> updateCourseSemester(@PathVariable Long id, @RequestBody CourseSemesterDTO courseSemesterDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, courseSemesterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSemester(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
