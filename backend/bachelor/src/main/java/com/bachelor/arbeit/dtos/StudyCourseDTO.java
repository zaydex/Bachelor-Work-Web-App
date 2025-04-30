package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.entities.StudyCourse;
import com.bachelor.arbeit.entities.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudyCourseDTO {
    private Long id;
    private String studyCourseName;
    private Integer numberOfSemesters;
    private TimePeriodDTO timePeriod;

    public static StudyCourseDTO mapToDTO(StudyCourse studyCourse) {
        TimePeriod timePeriod = studyCourse.getSemesters().isEmpty() ? null : studyCourse.getSemesters().get(0).getTimePeriod();
        TimePeriodDTO timePeriodDTO = timePeriod != null ? TimePeriodDTO.mapToDTO(timePeriod) : null;

        return StudyCourseDTO.builder()
                .id(studyCourse.getId())
                .studyCourseName(studyCourse.getStudyCourseName())
                .numberOfSemesters(studyCourse.getSemesters().size())
                .timePeriod(timePeriodDTO) // Передаем объект TimePeriodDTO
                .build();
    }

    public static StudyCourse mapToEntity(StudyCourseDTO studyCourseDTO, TimePeriod timePeriod) {
        StudyCourse studyCourse = StudyCourse.builder()
                .studyCourseName(studyCourseDTO.getStudyCourseName())
                .semesters(new ArrayList<>()) // Защита от null
                .build();

        int totalSemesters = studyCourseDTO.getNumberOfSemesters() != null ? studyCourseDTO.getNumberOfSemesters() : 0;

        for (int i = 1; i <= totalSemesters; i++) {
            Semester semester = new Semester();
            semester.setStudyCourse(studyCourse);
            semester.setSemesterNumber(i);
            semester.setTimePeriod(timePeriod);  // Используйте объект TimePeriod, а не DTO
            studyCourse.getSemesters().add(semester);
        }
        return studyCourse;
    }
}
