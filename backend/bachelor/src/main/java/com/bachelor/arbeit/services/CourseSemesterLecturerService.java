package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.CourseSemesterLecturerDTO;
import com.bachelor.arbeit.entities.CourseSemester;
import com.bachelor.arbeit.entities.CourseSemesterLecturer;
import com.bachelor.arbeit.entities.Lecturer;
import com.bachelor.arbeit.repos.CourseSemesterLecturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CourseSemesterLecturerService {
    private final CourseSemesterLecturerRepo repo;
    private final CourseSemesterService courseSemesterService;
    private final LecturerService lecturerService;


    @Autowired
    public CourseSemesterLecturerService(CourseSemesterLecturerRepo repo, CourseSemesterService courseSemesterService, LecturerService lecturerService) {
        this.repo = repo;
        this.courseSemesterService = courseSemesterService;
        this.lecturerService = lecturerService;
    }

    @Transactional
    public List<CourseSemesterLecturerDTO> getAll(){
        return repo.findAll()
                .stream()
                .map(CourseSemesterLecturerDTO::mapToDTO)
                .toList();
    }

    @Transactional
    public CourseSemesterLecturerDTO create(CourseSemesterLecturerDTO courseSemesterLecturerDTO){
        CourseSemester courseSemester = courseSemesterService.findById(courseSemesterLecturerDTO.getCourseSemester().getId());
        Lecturer lecturer = lecturerService.findById(courseSemesterLecturerDTO.getLecturer().getId());

        CourseSemesterLecturer courseSemesterLecturer = CourseSemesterLecturer.builder()
                .courseSemester(courseSemester)
                .lecturer(lecturer)
                .comment(courseSemesterLecturerDTO.getComment())
                .numberOfStudents(courseSemesterLecturerDTO.getNumberOfStudents())
                .numberOfGroups(courseSemesterLecturerDTO.getNumberOfGroups())
                .build();

        return CourseSemesterLecturerDTO.mapToDTO(repo.save(courseSemesterLecturer));
    }

    @Transactional
    public CourseSemesterLecturerDTO update(Long id, CourseSemesterLecturerDTO updated){
        CourseSemesterLecturer existing = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID not found with number: " + id));

        CourseSemester courseSemester = courseSemesterService.findById(updated.getCourseSemester().getId());
        Lecturer lecturer = lecturerService.findById(updated.getLecturer().getId());

        existing.setCourseSemester(courseSemester);
        existing.setLecturer(lecturer);
        existing.setComment(updated.getComment());
        existing.setNumberOfStudents(updated.getNumberOfStudents());
        existing.setNumberOfGroups(updated.getNumberOfGroups());

        return CourseSemesterLecturerDTO.mapToDTO(repo.save(existing));
    }

    @Transactional
    public void delete(Long id){
        repo.deleteById(id);
    }
}
