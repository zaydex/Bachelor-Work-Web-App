package com.bachelor.arbeit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Data
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyCourse {
    @Id
    @GeneratedValue()
    private Long id;
    @Column(unique = true)
    private String studyCourseName;

    //Study Course -> Semesters
    @OneToMany(mappedBy = "studyCourse", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id ASC")
    @JsonIgnore
    private List<Semester> semesters = new ArrayList<>();
}
