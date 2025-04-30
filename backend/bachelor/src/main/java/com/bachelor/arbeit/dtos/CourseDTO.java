package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseDTO {
    private Long id;
    private String courseName;
    private Integer weeklyLectureHours;
    private Integer weeklyExerciseHours;
    private Boolean isMandatory;
    private Integer creditPoints;

    public static Course mapToEntity(CourseDTO courseDTO) {
        return Course.builder()
                .courseName(courseDTO.getCourseName())
                .weeklyLectureHours(courseDTO.getWeeklyLectureHours())
                .weeklyExerciseHours(courseDTO.getWeeklyExerciseHours())
                .isMandatory(courseDTO.getIsMandatory())
                .creditPoints(courseDTO.getCreditPoints())
                .build();
    }
    public static CourseDTO mapToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .weeklyLectureHours(course.getWeeklyLectureHours())
                .weeklyExerciseHours(course.getWeeklyExerciseHours())
                .isMandatory(course.getIsMandatory())
                .creditPoints(course.getCreditPoints())
                .build();
    }
}
