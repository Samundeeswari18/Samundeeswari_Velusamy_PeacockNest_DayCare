package com.sam.velusamy_samundeeswari_peacocknestdaycare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

//Using lombok to reduce the boiler plate code
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Appointment {
    //primary key and autoincrement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    //Properties and variables for the table
    private String parentGuardianName;
    private LocalDate scheduleDate;
    private LocalTime scheduleTime;
    private String purpose;

}
