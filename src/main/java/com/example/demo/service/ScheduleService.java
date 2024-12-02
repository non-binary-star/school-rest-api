package com.example.demo.service;

import com.example.demo.model.Schedule;
import com.example.demo.model.ScheduleDto;
import com.example.demo.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        if (scheduleRepository.existsByClassIdAndSubjectIdAndTeacherIdAndDayOfWeekAndScheduleOrder(
                scheduleDto.getClassId(),
                scheduleDto.getTeacherId(),
                scheduleDto.getSubjectId(),
                scheduleDto.getDayOfWeek(),
                scheduleDto.getScheduleOrder())) {
            throw createHttpClientException(CONFLICT, "Schedule conflict for the same class and time.");
        }
        Schedule schedule = ScheduleMapper.toEntity(scheduleDto);
        return ScheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(ScheduleMapper::toDto)
                .toList();
    }

    public ScheduleDto getScheduleById(int id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> createHttpClientException(NOT_FOUND, "Schedule not found with id: " + id));
        return ScheduleMapper.toDto(schedule);
    }

    public ScheduleDto updateSchedule(int id, ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> createHttpClientException(NOT_FOUND, "Schedule not found with id: " + id));
        schedule.setClassId(scheduleDto.getClassId());
        schedule.setTeacherId(scheduleDto.getTeacherId());
        schedule.setSubjectId(scheduleDto.getSubjectId());
        schedule.setScheduleOrder(scheduleDto.getScheduleOrder());
        schedule.setDayOfWeek(scheduleDto.getDayOfWeek());
        schedule.setDate(scheduleDto.getDate());
        return ScheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    public void deleteSchedule(int id) {
        if (!scheduleRepository.existsById(id)) {
            throw createHttpClientException(NOT_FOUND, "Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }

    private HttpClientErrorException createHttpClientException(HttpStatus statusCode, String message) {
        return HttpClientErrorException.create(statusCode, message, null, null, null);
    }
}
