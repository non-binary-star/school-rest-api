package com.example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDto {
    private Integer scheduleId;
    private Integer classId;
    private Integer teacherId;
    private Integer subjectId;
    private Integer scheduleOrder;
    private Integer dayOfWeek;
    private LocalDate date;
}
