package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.LecturerTypeDTO;
import com.bachelor.arbeit.entities.LecturerType;
import com.bachelor.arbeit.repos.LecturerTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LecturerTypeService {
    private final LecturerTypeRepo repo;

    @Autowired
    public LecturerTypeService(LecturerTypeRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public List<LecturerTypeDTO> getLecturerTypes() {
        return repo.findAll()
                .stream()
                .map(LecturerTypeDTO::mapToDTO)
                .toList();
    }


    @Transactional
    public LecturerTypeDTO createLecturerType(LecturerTypeDTO lecturerTypeDTO) {
        LecturerType newType = LecturerTypeDTO.mapToEntity(lecturerTypeDTO);
        repo.save(newType);

        return LecturerTypeDTO.mapToDTO(newType);
    }

    @Transactional
    public LecturerTypeDTO updateLecturerType(Long id, LecturerTypeDTO lecturerTypeDTO) {
        LecturerType updateExistingLecturerType = repo.findById(id).orElseThrow();

        if(lecturerTypeDTO.getTypeName() != null) {
            updateExistingLecturerType.setTypeName(lecturerTypeDTO.getTypeName());
        }
        if(lecturerTypeDTO.getRequiredHours() != null) {
            updateExistingLecturerType.setRequiredHours(lecturerTypeDTO.getRequiredHours());
        }
        if(lecturerTypeDTO.getLectureship() != null) {
            updateExistingLecturerType.setLectureship(lecturerTypeDTO.getLectureship());
        }

        repo.save(updateExistingLecturerType);

        return LecturerTypeDTO.mapToDTO(updateExistingLecturerType);
    }

    @Transactional
    public boolean deleteLecturerType(Long id) {
        if(repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }

    public LecturerType findById(Long periodId) {
        return repo.findById(periodId)
                .orElseThrow(() -> new NoSuchElementException("Lecturer Type not found with ID:" + periodId));
    }
}
