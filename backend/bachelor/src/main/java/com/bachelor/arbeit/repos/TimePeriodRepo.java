package com.bachelor.arbeit.repos;

import com.bachelor.arbeit.entities.TimePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimePeriodRepo extends JpaRepository<TimePeriod, Long> {
}
