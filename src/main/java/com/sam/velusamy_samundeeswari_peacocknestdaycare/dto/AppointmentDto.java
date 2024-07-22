package com.sam.velusamy_samundeeswari_peacocknestdaycare.dto;



import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto{
    private Long appointmentId;

    @NotNull
    private String parentGuardianName;
    @NotNull
    private LocalDate scheduleDate;
    @NotNull
    private LocalTime scheduleTime;
    @NotNull
    private String purpose;
}
