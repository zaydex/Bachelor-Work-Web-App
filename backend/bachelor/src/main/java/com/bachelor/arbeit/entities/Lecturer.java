package com.bachelor.arbeit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true)
    private String name;
    private Integer teachingLoad;

    //LecturerTypes->Lecturer
    @ManyToOne
    @JoinColumn(name = "lecturer_type_id")
    private LecturerType lecturerType;

    //Lecturer -> CourseSemesterLecturers
    @OneToMany(mappedBy = "lecturer")
    private List<CourseSemesterLecturer> courseSemesterLecturers;
}