package com.example.demo.service;

import com.example.demo.model.Grade;
import com.example.demo.model.GradeDto;
import com.example.demo.repository.GradesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradesServiceTest {

    @Mock
    private GradesRepository gradesRepository;

    @InjectMocks
    private GradesService gradesService;

    @Test
    void testGetAllGrades() {
        Grade grade1 = new Grade();
        grade1.setGradeId(1);
        grade1.setStudentId(101);
        grade1.setScheduleId(201);
        grade1.setGrade(9);

        Grade grade2 = new Grade();
        grade2.setGradeId(2);
        grade2.setStudentId(102);
        grade2.setScheduleId(202);
        grade2.setGrade(8);

        when(gradesRepository.findAll()).thenReturn(List.of(grade1, grade2));

        List<GradeDto> result = gradesService.getAllGrades();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(gradesRepository, times(1)).findAll();
    }
}
