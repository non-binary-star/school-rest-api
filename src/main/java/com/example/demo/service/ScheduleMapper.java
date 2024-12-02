package com.example.demo.service;

import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleDto;

public class ScheduleMapper {

    public static ScheduleDto toDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setClassId(schedule.getClassId());
        dto.setTeacherId(schedule.getTeacherId());
        dto.setSubjectId(schedule.getSubjectId());
        dto.setScheduleOrder(schedule.getScheduleOrder());
        dto.setDayOfWeek(schedule.getDayOfWeek());
        dto.setDate(schedule.getDate());
        return dto;
    }

    public static Schedule toEntity(ScheduleDto dto) {
        Schedule schedule = new Schedule();
        schedule.setClassId(dto.getClassId());
        schedule.setTeacherId(dto.getTeacherId());
        schedule.setSubjectId(dto.getSubjectId());
        schedule.setDayOfWeek(dto.getDayOfWeek());
        schedule.setScheduleOrder(dto.getScheduleOrder());
        return schedule;
    }
}
