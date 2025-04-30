package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.SemesterDTO;
import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.repos.SemesterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SemesterService {
    private final SemesterRepo repo;

    @Autowired
    public SemesterService(SemesterRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public List<SemesterDTO> getSemestersByStudyCourse(Long id) {
        return repo.findAll()
                .stream()
                .filter(semester -> semester.getStudyCourse().getId().equals(id))
                .map(SemesterDTO::mapToDTO)
                .toList();
    }

    @Transactional
    public SemesterDTO updateNumberOfStudentsBySemesterId(Long id, SemesterDTO dto) {
        Semester semester = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Semester not found with ID:" + id));
        semester.setNumberOfStudents(dto.getNumberOfStudents());

        return SemesterDTO.mapToDTO(repo.save(semester));
    }


    public Semester findById(Long semesterId) {
        return repo.findById(semesterId)
                .orElseThrow(() -> new NoSuchElementException("ID not found with number: " + semesterId));
    }
}
