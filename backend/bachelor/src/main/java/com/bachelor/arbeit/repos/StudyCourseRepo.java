package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.StudyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StudyCourseRepo extends JpaRepository<StudyCourse, Long> {
}
