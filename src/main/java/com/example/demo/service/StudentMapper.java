package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDto;

public class StudentMapper {

    public static StudentDto toDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setDateOfBirth(student.getBirthDate());
        return dto;
    }

    public static Student toEntity(StudentDto dto) {
        Student student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setBirthDate(dto.getDateOfBirth());
        return student;
    }
}
