package com.bachelor.arbeit.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer semesterNumber;
    private Integer numberOfStudents = 0;

    //Semesters -> StudyCourse
    @ManyToOne
    @JoinColumn(name = "study_course_id")
    @JsonIgnore
    private StudyCourse studyCourse;

    //Semester -> CourseSemesters
    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseSemester> courseSemesters = new HashSet<>();

    //Semesters -> TimePeriod
    @ManyToOne
    @JoinColumn(name = "time_period_id")
    private TimePeriod timePeriod;
}
