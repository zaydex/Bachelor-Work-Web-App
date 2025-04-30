package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.CourseSemesterDTO;
import com.bachelor.arbeit.entities.Course;
import com.bachelor.arbeit.entities.CourseSemester;
import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.repos.CourseSemesterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseSemesterService {

    private static final String TEXT = "ID not found with number: ";

    private final CourseSemesterRepo repository;
    private final CourseService courseService;
    private final SemesterService semesterService;

    @Autowired
    public CourseSemesterService(CourseSemesterRepo repository, CourseService courseService, SemesterService semesterService) {
        this.repository = repository;
        this.courseService = courseService;
        this.semesterService = semesterService;
    }

    @Transactional
    public List<CourseSemesterDTO> getCourseSemesters(){
        return repository.findAll()
                .stream()
                .map(CourseSemesterDTO::mapToDTO)
                .toList();
    }

    @Transactional
    public CourseSemesterDTO getCourseSemesterById(Long id){
        return repository.findById(id)
                .map(CourseSemesterDTO::mapToDTO)
                .orElseThrow(() -> new NoSuchElementException(TEXT + id));
    }

    @Transactional
    public CourseSemesterDTO create(CourseSemesterDTO courseSemesterDTO){
        Course course = courseService.findById(courseSemesterDTO.getCourse().getId());
        Semester semester = semesterService.findById(courseSemesterDTO.getSemester().getId());

        CourseSemester newCourseSemester = CourseSemester.builder()
                .course(course)
                .semester(semester)
                .toPlan(courseSemesterDTO.getToPlan())
                .build();

        return CourseSemesterDTO.mapToDTO(repository.save(newCourseSemester));
    }

    @Transactional
    public CourseSemesterDTO update(Long id, CourseSemesterDTO updated){
        CourseSemester existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(TEXT + id));

        Course course = courseService.findById(updated.getCourse().getId());
        Semester semester = semesterService.findById(updated.getSemester().getId());

        existing.setCourse(course);
        existing.setSemester(semester);
        existing.setToPlan(updated.getToPlan());

        return CourseSemesterDTO.mapToDTO(repository.save(existing));
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    public CourseSemester findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException(TEXT + id));
    }
}
