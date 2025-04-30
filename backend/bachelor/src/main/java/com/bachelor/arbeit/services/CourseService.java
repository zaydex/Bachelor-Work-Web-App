package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.CourseDTO;
import com.bachelor.arbeit.entities.Course;
import com.bachelor.arbeit.repos.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepo repo;

    @Autowired
    public CourseService(CourseRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public Set<CourseDTO> getAllCourses() {
        return repo.findAll().stream()
                .map(CourseDTO::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public CourseDTO createCourse(CourseDTO courseDTO) {
        Course newCourse = CourseDTO.mapToEntity(courseDTO);

        return CourseDTO.mapToDTO(repo.save(newCourse));
    }

    @Transactional
    public CourseDTO updateCourse(Long id, CourseDTO courseDTO) {
        Course existingCourse = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));

        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setWeeklyLectureHours(courseDTO.getWeeklyLectureHours());
        existingCourse.setWeeklyExerciseHours(courseDTO.getWeeklyExerciseHours());
        existingCourse.setIsMandatory(courseDTO.getIsMandatory());
        existingCourse.setCreditPoints(courseDTO.getCreditPoints());

        return CourseDTO.mapToDTO(repo.save(existingCourse));
    }

    @Transactional
    public void deleteCourse(Long courseId) {
        repo.deleteById(courseId);
    }

    public Course findById(Long courseId) {
        return repo.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("ID not found with number: " + courseId));
    }
}
