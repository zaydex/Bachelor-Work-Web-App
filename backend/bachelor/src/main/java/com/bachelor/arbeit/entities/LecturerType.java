package com.bachelor.arbeit.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"typeName", "requiredHours"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturerType {
    @Id
    @GeneratedValue()
    private Long id;
    private String typeName;
    private Integer requiredHours;
    private Boolean lectureship;

    //Lecturer->LecturerTypes
    @OneToMany(mappedBy = "lecturerType")
    private Set<Lecturer> lecturers;
}
