package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.CourseSemester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseSemesterRepo extends JpaRepository<CourseSemester, Long> {
}
