package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.GradeDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GradeMapper {

    public static GradeDto toDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setStudentId(grade.getStudentId());
        gradeDto.setScheduleId(grade.getScheduleId());
        gradeDto.setGrade(grade.getGrade());
        return gradeDto;
    }

    public static Grade toEntity(GradeDto gradeDto) {
        Grade grade = new Grade();
        grade.setStudentId(gradeDto.getStudentId());
        grade.setScheduleId(gradeDto.getScheduleId());
        grade.setGrade(gradeDto.getGrade());
        return grade;
    }

    public static List<GradeDto> toDtoList(List<Grade> grades) {
        if (grades == null) {
            return null;
        }
        return grades.stream()
                .map(GradeMapper::toDto)
                .collect(Collectors.toList());
    }

    public static List<Grade> toEntityList(List<GradeDto> gradeDtos) {
        if (gradeDtos == null) {
            return null;
        }
        return gradeDtos.stream()
                .map(GradeMapper::toEntity)
                .collect(Collectors.toList());
    }
}
