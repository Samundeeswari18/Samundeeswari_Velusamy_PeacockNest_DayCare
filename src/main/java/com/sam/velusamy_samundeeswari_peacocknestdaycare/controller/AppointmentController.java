package com.sam.velusamy_samundeeswari_peacocknestdaycare.controller;

import com.sam.velusamy_samundeeswari_peacocknestdaycare.dto.AppointmentDto;
import com.sam.velusamy_samundeeswari_peacocknestdaycare.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // Show the form for creating a new appointment
    @GetMapping("/create")
    public String showCreateAppointmentForm(Model model) {
        model.addAttribute("appointmentDto", new AppointmentDto());
        return "createAppointment";
    }

    // Display the list of all appointments
    @GetMapping("/list")
    public String showAllAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.findAllAppointments());
        return "appointments";
    }

    // Handle the form submission for creating a new appointment
    @PostMapping("/create")
    public String createAppointment(@Valid @ModelAttribute AppointmentDto appointmentDto, Model model) {
        appointmentService.createAppointment(appointmentDto);
        model.addAttribute("appointments", new AppointmentDto());
        return "redirect:/appointments/create?success";
    }

    // Handle the deletion of an appointment by its ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
}
