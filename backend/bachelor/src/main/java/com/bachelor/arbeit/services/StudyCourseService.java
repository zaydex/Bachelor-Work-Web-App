package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.StudyCourseDTO;
import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.entities.StudyCourse;
import com.bachelor.arbeit.entities.TimePeriod;
import com.bachelor.arbeit.repos.StudyCourseRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StudyCourseService {
    private final StudyCourseRepo repo;
    private final TimePeriodService timePeriodService;

    @Autowired
    public StudyCourseService(StudyCourseRepo repo, TimePeriodService timePeriodService) {
        this.repo = repo;
        this.timePeriodService = timePeriodService;
    }

    @Transactional
    public Set<StudyCourseDTO> getStudyCourses() {
        return repo.findAll()
                .stream()
                .map(StudyCourseDTO::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Transactional
    public StudyCourseDTO createStudyCourse(StudyCourseDTO studyCourseDTO) {
        TimePeriod timePeriod = timePeriodService.findById(studyCourseDTO.getTimePeriod().getId());
        if (timePeriod == null) {
            throw new EntityNotFoundException("TimePeriod not found with ID: " + studyCourseDTO.getTimePeriod().getId());
        }

        StudyCourse newStudyCourse = StudyCourseDTO.mapToEntity(studyCourseDTO, timePeriod);
        repo.save(newStudyCourse);

        return StudyCourseDTO.mapToDTO(newStudyCourse);
    }

    @Transactional
    public StudyCourseDTO updateStudyCourse(Long id, StudyCourseDTO studyCourseDTO) {
        StudyCourse existingStudyCourse = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("StudyCourse not found with ID:" + id));

        // Обновление TimePeriod
        if(studyCourseDTO.getTimePeriod() != null && studyCourseDTO.getTimePeriod().getId() != null) {
            TimePeriod newTimePeriod = timePeriodService.findById(studyCourseDTO.getTimePeriod().getId());

            // Обновляем TimePeriod у всех семестров, связанных с этим StudyCourse
            existingStudyCourse.getSemesters().forEach(semester -> semester.setTimePeriod(newTimePeriod));
        }

        updateNameIfPresent(existingStudyCourse, studyCourseDTO);
        updateSemesterIfPresent(existingStudyCourse, studyCourseDTO);

        repo.save(existingStudyCourse);

        return StudyCourseDTO.mapToDTO(existingStudyCourse);
    }

    @Transactional
    public boolean deleteStudyCourse(Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }


    private void updateSemesterIfPresent(StudyCourse existingStudyCourse, StudyCourseDTO requiredStudyCourseDTO) {
        Integer requiredSemesters = requiredStudyCourseDTO.getNumberOfSemesters();

        if (requiredSemesters != null) {
            int currentSemesters = existingStudyCourse.getSemesters().size();

            if (requiredSemesters < currentSemesters) {
                removeExcessSemesters(existingStudyCourse, currentSemesters - requiredSemesters);
            } else if (requiredSemesters > currentSemesters) {
                addMissingSemesters(existingStudyCourse, requiredSemesters - currentSemesters);
            }
        }
    }

    private void removeExcessSemesters(StudyCourse existingStudyCourse, int toRemove) {
        List<Semester> semesters = existingStudyCourse.getSemesters();
        for (int i = 0; i < toRemove; i++) {
            semesters.remove(semesters.size() - 1); // Удаляет последний элемент
        }
    }

    private void addMissingSemesters(StudyCourse existingStudyCourse, int toAdd) {
        for (int i = 0; i < toAdd; i++) {
            Semester semester = new Semester();
            semester.setStudyCourse(existingStudyCourse);
            existingStudyCourse.getSemesters().add(semester);
        }
    }

    private void updateNameIfPresent(StudyCourse existingStudyCourse, StudyCourseDTO requiredStudyCourseDTO) {
        if(requiredStudyCourseDTO.getStudyCourseName() != null) {
            existingStudyCourse.setStudyCourseName(requiredStudyCourseDTO.getStudyCourseName());
        }
    }
}
