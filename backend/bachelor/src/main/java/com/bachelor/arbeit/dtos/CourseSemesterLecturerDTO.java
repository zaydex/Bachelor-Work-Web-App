package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.CourseSemester;
import com.bachelor.arbeit.entities.CourseSemesterLecturer;
import com.bachelor.arbeit.entities.Lecturer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseSemesterLecturerDTO {
    private Long id;
    private CourseSemesterDTO courseSemester;
    private LecturerDTO lecturer;
    private String comment;
    private Integer numberOfStudents;
    private Integer numberOfGroups;

    public static CourseSemesterLecturerDTO mapToDTO(CourseSemesterLecturer courseSemesterLecturer){
        CourseSemester courseSemester = courseSemesterLecturer.getCourseSemester();
        CourseSemesterDTO courseSemesterDTO = courseSemester != null ? CourseSemesterDTO.mapToDTO(courseSemester) : null;

        Lecturer lecturer = courseSemesterLecturer.getLecturer();
        LecturerDTO lecturerDTO = lecturer != null ? LecturerDTO.mapToDTO(lecturer) : null;

        return CourseSemesterLecturerDTO.builder()
                .id(courseSemesterLecturer.getId())
                .courseSemester(courseSemesterDTO)
                .lecturer(lecturerDTO)
                .comment(courseSemesterLecturer.getComment())
                .numberOfStudents(courseSemesterLecturer.getNumberOfStudents())
                .numberOfGroups(courseSemesterLecturer.getNumberOfGroups())
                .build();
    }
}
