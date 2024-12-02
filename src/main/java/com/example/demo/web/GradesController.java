package com.example.demo.web;

import com.example.demo.model.GradeDto;
import com.example.demo.service.GradesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grades")
public class GradesController {

    private final GradesService gradesService;

//    @PreAuthorize("hasAuthority('TEACHER')")
    @PostMapping
    public ResponseEntity<GradeDto> createGrade(@RequestBody @Valid GradeDto gradeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gradesService.createGrade(gradeDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public List<GradeDto> getAllGrades() {
        return gradesService.getAllGrades();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public ResponseEntity<GradeDto> getGradeById(@PathVariable int id) {
        return ResponseEntity.ok(gradesService.getGradeById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<GradeDto> updateGrade(@PathVariable int id, @RequestBody @Valid GradeDto gradeDto) {
        return ResponseEntity.ok(gradesService.updateGrade(id, gradeDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TEACHER')")
    public ResponseEntity<Void> deleteGrade(@PathVariable int id) {
        gradesService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
}
