package com.bachelor.arbeit.services;

import com.bachelor.arbeit.dtos.TimePeriodDTO;
import com.bachelor.arbeit.entities.TimePeriod;
import com.bachelor.arbeit.repos.TimePeriodRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TimePeriodService {
    private final TimePeriodRepo repo;

    @Autowired
    public TimePeriodService(TimePeriodRepo repo) {
        this.repo = repo;
    }

    @Transactional
    public List<TimePeriodDTO> getAllTimePeriods() {
        return repo.findAll()
                .stream()
                .map(TimePeriodDTO::mapToDTO)
                .toList();
    }

    @Transactional
    public TimePeriodDTO createTimePeriod(TimePeriodDTO timePeriodDTO) {
        TimePeriod newTimePeriod = TimePeriodDTO.mapToEntity(timePeriodDTO);
        return TimePeriodDTO.mapToDTO(repo.save(newTimePeriod));
    }

    @Transactional
    public TimePeriodDTO updateTimePeriod(Long id, TimePeriodDTO timePeriodDTO){
        TimePeriod existingTimePeriod = repo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("ID not found with number: " + id));

        existingTimePeriod.setAcademicYear(timePeriodDTO.getAcademicYear());
        existingTimePeriod.setSemesters(timePeriodDTO.getSemesters());

        return TimePeriodDTO.mapToDTO(repo.save(existingTimePeriod));
    }

    @Transactional
    public void deleteTimePeriod(Long id){
        if(repo.existsById(id)){
            repo.deleteById(id);
        }
        else{
            throw new EntityNotFoundException("TimePeriod not found with ID: " + id);
        }
    }

    public TimePeriod findById(Long periodId) {
        return repo.findById(periodId)
                .orElseThrow(() -> new NoSuchElementException("Time Period not found with ID:" + periodId));
    }
}
