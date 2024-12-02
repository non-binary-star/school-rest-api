package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Grades", uniqueConstraints = @UniqueConstraint(columnNames = {"StudentId", "ScheduleId"}))
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"GradeiD\"")
    private Integer gradeId;

    @NotNull
    @Column(name = "\"StudentId\"")
    private Integer studentId;

    @NotNull
    @Column(name = "\"ScheduleId\"")
    private Integer scheduleId;

    @Min(1)
    @Max(10)
    @Column(name = "\"Grade\"")
    private Integer grade;
}


