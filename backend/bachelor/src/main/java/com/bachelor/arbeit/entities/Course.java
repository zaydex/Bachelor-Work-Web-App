package com.bachelor.arbeit.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true)
    private String courseName;
    private Integer weeklyLectureHours;
    private Integer weeklyExerciseHours;
    private Boolean isMandatory;
    private Integer creditPoints;

    //Course -> CourseSemesters
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CourseSemester> courseSemesters = new HashSet<>();
}
