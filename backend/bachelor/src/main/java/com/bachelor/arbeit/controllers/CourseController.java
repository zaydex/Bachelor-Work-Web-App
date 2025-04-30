package com.bachelor.arbeit.controllers;

import com.bachelor.arbeit.dtos.CourseDTO;
import com.bachelor.arbeit.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/course")
public class CourseController {
    private final CourseService service;

    @Autowired
    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<CourseDTO>> getAllCourses(){
        return ResponseEntity.ok(service.getAllCourses());
    }

    @PostMapping
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(courseDTO));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Long courseId, @RequestBody CourseDTO courseDTO){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(service.updateCourse(courseId, courseDTO));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long courseId){
        service.deleteCourse(courseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
