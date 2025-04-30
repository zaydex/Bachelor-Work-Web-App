package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.CourseSemesterLecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSemesterLecturerRepo extends JpaRepository<CourseSemesterLecturer, Long> {
}
