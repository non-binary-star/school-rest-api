package com.example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDto {
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private LocalDate enrollmentDate;
}
