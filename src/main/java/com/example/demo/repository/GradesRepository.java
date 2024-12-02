package com.example.demo.repository;

import com.example.demo.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradesRepository extends JpaRepository<Grade, Integer> {

    boolean existsByStudentIdAndScheduleId(int studentId, int scheduleId);

    boolean existsByStudentIdAndScheduleIdAndGradeIdNot(int studentId, int scheduleId, int gradeId);
}
