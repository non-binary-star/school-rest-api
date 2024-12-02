package com.example.demo.repository;

import com.example.demo.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    boolean existsByClassIdAndSubjectIdAndTeacherIdAndDayOfWeekAndScheduleOrder(Integer classId, Integer subjectId, Integer teacherId, Integer dayOfWeek, Integer scheduleOrder);
}
