package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    private String parentGuardianName;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private String purpose;

}
