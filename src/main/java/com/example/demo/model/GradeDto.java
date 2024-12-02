package com.example.demo.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDto {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer scheduleId;

    @Min(1)
    @Max(10)
    private Integer grade;
}

