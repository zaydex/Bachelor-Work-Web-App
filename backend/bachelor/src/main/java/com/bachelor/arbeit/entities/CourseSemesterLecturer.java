package com.bachelor.arbeit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseSemesterLecturer {
    @Id
    @GeneratedValue()
    private Long id;

    //CourseSemesterLecturers -> CourseSemester
    @ManyToOne
    @JoinColumn(name = "course_semester_id")
    private CourseSemester courseSemester;

    //CourseSemesterLecturers -> Lecturer
    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer lecturer;


    private String comment;
    private Integer numberOfStudents;
    private Integer numberOfGroups;
}
