package com.bachelor.arbeit.dtos;

import com.bachelor.arbeit.entities.Semester;
import com.bachelor.arbeit.entities.TimePeriod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TimePeriodDTO {
    private Long id;
    private String academicYear;
    private List<Semester> semesters;

    // Метод для преобразования сущности в DTO
    public static TimePeriodDTO mapToDTO(TimePeriod timePeriod) {
        return TimePeriodDTO.builder()
                .id(timePeriod.getId())
                .academicYear(timePeriod.getAcademicYear())
                .build();  // Исключаем семестры
    }

    public static TimePeriod mapToEntity(TimePeriodDTO timePeriodDTO) {
        return TimePeriod.builder()
                .id(timePeriodDTO.getId())
                .academicYear(timePeriodDTO.getAcademicYear())
                .semesters(timePeriodDTO.getSemesters())
                .build();
    }
}
