package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"StudentID\"")
    private Integer studentId;
    @Column(name = "\"FirstName\"")
    private String firstName;
    @Column(name = "\"LastName\"")
    private String lastName;
    @Column(name = "\"BirthDate\"")
    private LocalDate birthDate;
}
