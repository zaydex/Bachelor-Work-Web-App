package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Course;
import com.bachelor.arbeit.entities.CourseSemester;
import com.bachelor.arbeit.entities.Semester;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseSemesterDTO {
    private Long id;
    private CourseDTO course;
    private SemesterDTO semester;
    private Boolean toPlan = false;

    public static CourseSemesterDTO mapToDTO (CourseSemester courseSemester){
        Course course = courseSemester.getCourse();
        CourseDTO courseDTO = course != null ? CourseDTO.mapToDTO(course) : null;

        Semester semester = courseSemester.getSemester();
        SemesterDTO semesterDTO = semester != null ? SemesterDTO.mapToDTO(semester) : null;

        return CourseSemesterDTO.builder()
                .id(courseSemester.getId())
                .course(courseDTO)
                .semester(semesterDTO)
                .toPlan(courseSemester.getToPlan() != null ? courseSemester.getToPlan() : false)
                .build();
    }
}
