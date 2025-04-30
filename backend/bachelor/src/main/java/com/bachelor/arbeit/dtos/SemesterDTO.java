package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.entities.StudyCourse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDTO {
    private Long id;
    private Integer semesterNumber;
    private Integer numberOfStudents;
    private StudyCourseDTO studyCourse;

    public static SemesterDTO mapToDTO(Semester semester){
        StudyCourse studyCourse = semester.getStudyCourse();
        StudyCourseDTO studyCourseDTO = studyCourse != null ? StudyCourseDTO.mapToDTO(studyCourse) : null;

        return SemesterDTO.builder()
                .id(semester.getId())
                .semesterNumber(semester.getSemesterNumber())
                .numberOfStudents(semester.getNumberOfStudents())
                .studyCourse(studyCourseDTO)
                .build();
    }

    public static Semester mapToEntity(SemesterDTO semesterDTO){
        return Semester.builder()
                .id(semesterDTO.getId())
                .semesterNumber(semesterDTO.getSemesterNumber())
                .numberOfStudents(semesterDTO.getNumberOfStudents())
                .build();
    }
}
