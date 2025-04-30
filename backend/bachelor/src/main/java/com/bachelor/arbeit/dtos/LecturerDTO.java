package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Lecturer;
import com.bachelor.arbeit.entities.LecturerType;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class LecturerDTO {
    private Long id;
    private String name;
    private Integer teachingLoad;
    private LecturerTypeDTO lecturerType;

    public static LecturerDTO mapToDTO(Lecturer lecturer){
        LecturerType lecturerType = lecturer.getLecturerType();
        LecturerTypeDTO lecturerTypeDTO = lecturerType != null ? LecturerTypeDTO.mapToDTO(lecturerType) : null;

        return LecturerDTO.builder()
                .id(lecturer.getId())
                .name(lecturer.getName())
                .teachingLoad(lecturer.getTeachingLoad())
                .lecturerType(lecturerTypeDTO)
                .build();
    }

    public static Lecturer mapToEntity(LecturerDTO lecturerDTO, LecturerType lecturerType){
        return Lecturer.builder()
                .name(lecturerDTO.getName())
                .teachingLoad(lecturerDTO.getTeachingLoad())
                .lecturerType(lecturerType)
                .build();
    }
}
