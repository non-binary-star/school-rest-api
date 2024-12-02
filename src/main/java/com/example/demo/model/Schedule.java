package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity(name = "Schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"ScheduleId\"")
    private Integer scheduleId;
    @Column(name = "\"ClassId\"")
    private Integer classId;
    @Column(name = "\"TeacherId\"")
    private Integer teacherId;
    @Column(name = "\"SubjectId\"")
    private Integer subjectId;
    @Column(name = "\"ScheduleOrder\"")
    private Integer scheduleOrder;
    @Column(name = "\"DayOfWeek\"")
    private Integer dayOfWeek;
    @Column(name = "\"Date\"")
    private LocalDate date;
}
