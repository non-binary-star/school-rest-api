package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.GradeDto;
import com.example.demo.repository.GradesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class GradesService {

    private final GradesRepository gradesRepository;

    public GradeDto createGrade(GradeDto gradeDto) {
        if (gradesRepository.existsByStudentIdAndScheduleId(gradeDto.getStudentId(), gradeDto.getScheduleId())) {
            throw createHttpClientException(CONFLICT, "Grade for this student and schedule already exists.");
        }
        Grade grade = new Grade();
        grade.setStudentId(gradeDto.getStudentId());
        grade.setScheduleId(gradeDto.getScheduleId());
        grade.setGrade(gradeDto.getGrade());
        return GradeMapper.toDto(gradesRepository.save(grade));
    }

    public List<GradeDto> getAllGrades() {
        return gradesRepository.findAll().stream()
                .map(GradeMapper::toDto)
                .toList();
    }

    public GradeDto getGradeById(int id) {
        Grade grade = gradesRepository.findById(id)
                .orElseThrow(() ->createHttpClientException(NOT_FOUND, "Grade not found with id: " + id));
        return GradeMapper.toDto(grade);
    }

    public GradeDto updateGrade(int id, GradeDto gradeDto) {
        Grade grade = gradesRepository.findById(id)
                .orElseThrow(() -> createHttpClientException(NOT_FOUND, "Grade not found with id: " + id));
        grade.setStudentId(gradeDto.getStudentId());
        grade.setScheduleId(gradeDto.getScheduleId());
        grade.setGrade(gradeDto.getGrade());
        return GradeMapper.toDto(gradesRepository.save(grade));
    }

    public void deleteGrade(int id) {
        if (!gradesRepository.existsById(id)) {
            throw createHttpClientException(NOT_FOUND, "Grade not found with id: " + id);
        }
        gradesRepository.deleteById(id);
    }

    private HttpClientErrorException createHttpClientException(HttpStatusCode statusCode, String message) {
        return HttpClientErrorException.create(statusCode, message, null, null, null);
    }
}
