package com.example.demo.web;

import com.example.demo.model.ScheduleDto;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ScheduleDto> createSchedule(@RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(scheduleDto));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER', 'STUDENT')")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable int id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable int id, @RequestBody ScheduleDto scheduleDto) {
        return ResponseEntity.ok(scheduleService.updateSchedule(id, scheduleDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteSchedule(@PathVariable int id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
