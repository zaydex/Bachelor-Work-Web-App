package com.bachelor.arbeit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "course_semester")
public class CourseSemester {
    @Id
    @GeneratedValue()
    private Long id;

    //CourseSemesters -> Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    //CourseSemesters -> Semester
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    //CourseSemester -> CourseSemesterLecturers
    @OneToMany(mappedBy = "courseSemester")
    private List<CourseSemesterLecturer> courseSemesterLecturers;

    private Boolean toPlan = false;
}
