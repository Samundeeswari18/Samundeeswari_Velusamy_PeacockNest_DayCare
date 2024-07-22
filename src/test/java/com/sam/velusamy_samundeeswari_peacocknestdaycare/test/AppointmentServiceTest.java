package com.sam.velusamy_samundeeswari_peacocknestdaycare.test;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.AppointmentDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.entity.Appointment;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.repository.AppointmentRepository;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    @InjectMocks
    private AppointmentService appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAppointment() {
        // Arrange
        AppointmentDto appointmentDto = new AppointmentDto(null, "John Doe", LocalDate.now(), LocalTime.now(), "Meeting");
        Appointment appointment = new Appointment(null, "John Doe", LocalDate.now(), LocalTime.now(), "Meeting");
        Appointment savedAppointment = new Appointment(1L, "John Doe", LocalDate.now(), LocalTime.now(), "Meeting");

        when(appointmentRepository.save(any(Appointment.class))).thenReturn(savedAppointment);

        // Act
        AppointmentDto result = appointmentService.createAppointment(appointmentDto);

        // Assert
        assertEquals(1L, result.getAppointmentId());
        assertEquals("John Doe", result.getParentGuardianName());
        assertEquals(appointmentDto.getScheduleDate(), result.getScheduleDate());
        assertEquals(appointmentDto.getScheduleTime(), result.getScheduleTime());
        assertEquals("Meeting", result.getPurpose());
    }

    @Test
    public void testDeleteAppointment() {
        // Act
        appointmentService.deleteAppointment(1L);

        // Assert
        verify(appointmentRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testFindAllAppointments() {
        // Arrange
        Appointment appointment = new Appointment(1L, "John Doe", LocalDate.now(), LocalTime.now(), "Meeting");
        when(appointmentRepository.findAll()).thenReturn(List.of(appointment));

        // Act
        Object result = appointmentService.findAllAppointments();

        // Assert
        assertEquals(1, ((List<?>) result).size());
    }
}
