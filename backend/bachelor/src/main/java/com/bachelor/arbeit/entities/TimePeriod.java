package com.bachelor.arbeit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimePeriod {
    @Id
    @GeneratedValue
    private Long id;

    private String academicYear;

    @OneToMany(mappedBy = "timePeriod")
    @JsonIgnore
    private List<Semester> semesters;
}
