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


    //Creates a new appointment based on the provided AppointmentDto and saves it to the database.
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = convertToEntity(appointmentDto);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return convertToDto(savedAppointment);
    }


    /**
     * Deletes an appointment by its ID.
     *
     * @param id the ID of the appointment to delete
     */
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }


    /**
     * Converts an Appointment entity to an AppointmentDto.
     *
     * @param appointment the Appointment entity to convert
     * @return the corresponding AppointmentDto
     */
    private AppointmentDto convertToDto(Appointment appointment) {
        return new AppointmentDto(
                appointment.getAppointmentId(),
                appointment.getParentGuardianName(),
                appointment.getScheduleDate(),
                appointment.getScheduleTime(),
                appointment.getPurpose()
        );
    }

    /**
     * Converts an AppointmentDto to an Appointment entity.
     *
     * @param appointmentDto the AppointmentDto to convert
     * @return the corresponding Appointment entity
     */
    private Appointment convertToEntity(AppointmentDto appointmentDto) {
        return new Appointment(
                appointmentDto.getAppointmentId(),
                appointmentDto.getParentGuardianName(),
                appointmentDto.getScheduleDate(),
                appointmentDto.getScheduleTime(),
                appointmentDto.getPurpose()
        );
    }

    /**
     * Retrieves all appointments from the repository.
     *
     * @return a list of all Appointment entities
     */
    public Object findAllAppointments() {
        return appointmentRepository.findAll();
    }
}
