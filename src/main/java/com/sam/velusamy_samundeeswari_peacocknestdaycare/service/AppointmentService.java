package com.sam.velusamy_samundeeswari_peacocknestdaycare.service;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.AppointmentDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Appointment;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;



    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = convertToEntity(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDto(savedAppointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDto convertToDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getAppointmentId(),
                appointment.getParentGuardianName(),
                appointment.getScheduleDate(),
                appointment.getScheduleTime(),
                appointment.getPurpose()
        );
    }

    private Appointment convertToEntity(AppointmentDto appointmentDto) {
        return new Appointment(
                appointmentDto.getAppointmentId(),
                appointmentDto.getParentGuardianName(),
                appointmentDto.getScheduleDate(),
                appointmentDto.getScheduleTime(),
                appointmentDto.getPurpose()
        );
    }

    public Object findAllAppointments() {
        return appointmentRepository.findAll();
    }
}
