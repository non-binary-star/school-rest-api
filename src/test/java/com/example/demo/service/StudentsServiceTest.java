package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDto;
import com.example.demo.repository.StudentsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentsServiceTest {

    @Mock
    private StudentsRepository studentsRepository;

    @InjectMocks
    private StudentsService studentsService;

    @Test
    void testCreateStudent() {
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("John");
        studentDto.setLastName("Doe");
        studentDto.setDateOfBirth(LocalDate.of(2000, 1, 1));
        studentDto.setEnrollmentDate(LocalDate.now());

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setBirthDate(studentDto.getDateOfBirth());

        when(studentsRepository.save(any(Student.class))).thenReturn(student);

        StudentDto result = studentsService.createStudent(studentDto);

        assertNotNull(result);
        assertEquals(studentDto.getFirstName(), result.getFirstName());
        verify(studentsRepository, times(1)).save(any(Student.class));
    }
}
