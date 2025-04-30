package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LecturerRepo extends JpaRepository<Lecturer, Long> {
}