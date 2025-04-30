package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SemesterRepo extends JpaRepository<Semester, Long> {

}
