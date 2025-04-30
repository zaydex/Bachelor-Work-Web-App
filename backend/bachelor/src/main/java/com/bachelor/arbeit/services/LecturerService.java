package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.LecturerDTO;
import com.bachelor.arbeit.entities.Lecturer;
import com.bachelor.arbeit.entities.LecturerType;
import com.bachelor.arbeit.repos.LecturerRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LecturerService {
    private final LecturerRepo repo;
    private final LecturerTypeService lecturerTypeService;
    @Autowired
    public LecturerService(LecturerRepo repo, LecturerTypeService lecturerTypeService) {
        this.repo = repo;
        this.lecturerTypeService = lecturerTypeService;
    }

    @Transactional
    public List<LecturerDTO> getLecturers() {
        return repo.findAll()
                .stream()
                .map(LecturerDTO::mapToDTO)
                .toList();
    }

    @Transactional
    public LecturerDTO createLecturer(LecturerDTO lecturerDTO) {
        LecturerType lecturerType = lecturerTypeService.findById(lecturerDTO.getLecturerType().getId());
        if(lecturerType == null){
            throw new EntityNotFoundException("TimePeriod not found with ID: " + lecturerDTO.getLecturerType().getId());
        }

        Lecturer newLecturer = LecturerDTO.mapToEntity(lecturerDTO, lecturerType);
        repo.save(newLecturer);

        return LecturerDTO.mapToDTO(newLecturer);
    }

    @Transactional
    public LecturerDTO updateLecturer(Long id, LecturerDTO lecturerDTO) {
        Lecturer existingLecturer = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lecturer not found with ID: " + id));

        if(lecturerDTO.getName() != null) {
            existingLecturer.setName(lecturerDTO.getName());
        }
        if(lecturerDTO.getLecturerType().getId() != null){
            LecturerType newLecturerType = lecturerTypeService.findById(lecturerDTO.getLecturerType().getId());
            existingLecturer.setLecturerType(newLecturerType);
        }

        repo.save(existingLecturer);

        return LecturerDTO.mapToDTO(existingLecturer);
    }

    @Transactional
    public boolean deleteLecturer(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public Lecturer findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID not found with number: " + id));
    }
}
