package com.example.demo.controller;

import com.example.demo.model.StudentDto;
import com.example.demo.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentsService.createStudent(studentDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentsService.getAllStudents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable int id) {
        return ResponseEntity.ok(studentsService.getStudentById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable int id, @RequestBody StudentDto studentDto) {
        return ResponseEntity.ok(studentsService.updateStudent(id, studentDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        studentsService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
