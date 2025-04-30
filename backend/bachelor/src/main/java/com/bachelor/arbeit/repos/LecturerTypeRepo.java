package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.LecturerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturerTypeRepo extends JpaRepository<LecturerType, Long> {
}
