package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.model.StudentDto;
import com.example.demo.repository.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StudentsService {

    private final StudentsRepository studentsRepository;

    public StudentDto createStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setBirthDate(studentDto.getDateOfBirth());
        return StudentMapper.toDto(studentsRepository.save(student));
    }

    public List<StudentDto> getAllStudents() {
        return studentsRepository.findAll().stream()
                .map(StudentMapper::toDto)
                .toList();
    }

    public StudentDto getStudentById(int id) {
        Student student = studentsRepository.findById(id)
                .orElseThrow(() -> createHttpClientException(NOT_FOUND, "Student not found with id: " + id));
        return StudentMapper.toDto(student);
    }

    public StudentDto updateStudent(int id, StudentDto studentDto) {
        Student student = studentsRepository.findById(id)
                .orElseThrow(() -> createHttpClientException(NOT_FOUND, "Student not found with id: " + id));
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setBirthDate(studentDto.getDateOfBirth());
        return StudentMapper.toDto(studentsRepository.save(student));
    }

    public void deleteStudent(int id) {
        if (!studentsRepository.existsById(id)) {
            throw createHttpClientException(NOT_FOUND, "Student not found with id: " + id);
        }
        studentsRepository.deleteById(id);
    }

    private HttpClientErrorException createHttpClientException(HttpStatus statusCode, String message) {
        return HttpClientErrorException.create(statusCode, message, null, null, null);
    }
}
