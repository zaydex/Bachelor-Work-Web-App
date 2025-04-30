package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.LecturerType;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class LecturerTypeDTO {
    private Long id;
    private String typeName;
    private Integer requiredHours;
    private Boolean lectureship;

    public static LecturerTypeDTO mapToDTO(LecturerType lecturerType){
        return LecturerTypeDTO.builder()
                .id(lecturerType.getId())
                .typeName(lecturerType.getTypeName())
                .requiredHours(lecturerType.getRequiredHours())
                .lectureship(lecturerType.getLectureship())
                .build();
    }
    public static LecturerType mapToEntity(LecturerTypeDTO lecturerTypeDTO){
        return LecturerType.builder()
                .typeName(lecturerTypeDTO.typeName)
                .requiredHours(lecturerTypeDTO.requiredHours)
                .lectureship(lecturerTypeDTO.lectureship)
                .build();
    }
}
