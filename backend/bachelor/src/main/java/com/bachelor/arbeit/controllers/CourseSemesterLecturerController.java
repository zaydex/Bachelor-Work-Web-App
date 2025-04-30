package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.CourseSemesterLecturerDTO;
import com.bachelor.arbeit.services.CourseSemesterLecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/course-semester-lecturer")
public class CourseSemesterLecturerController {

    private final CourseSemesterLecturerService service;

    @Autowired
    public CourseSemesterLecturerController(CourseSemesterLecturerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CourseSemesterLecturerDTO>> getCourseSemesterLecturers(){
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<CourseSemesterLecturerDTO> createCourseSemesterLecturer(@RequestBody CourseSemesterLecturerDTO courseSemesterLecturerDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(courseSemesterLecturerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseSemesterLecturerDTO> updateCourseSemesterLecturer(@PathVariable Long id, @RequestBody CourseSemesterLecturerDTO courseSemesterLecturerDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.update(id, courseSemesterLecturerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseSemesterLecturer(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
