package com.sam.velusamy_samundeeswari_peacocknestdaycare.repository;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Arrays;

public interface    AppointmentRepository extends JpaRepository<Appointment, Long> {
    Arrays findByScheduleDate(LocalDate date);
}
