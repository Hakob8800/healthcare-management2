package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @GetMapping
    public String allAppointments(ModelMap modelMap) {
        modelMap.addAttribute("appointmentList", appointmentRepository.findAll());
        return "allAppointments";
    }

    @GetMapping("/add")
    public String addAppointmentGet(ModelMap modelMap) {
        modelMap.addAttribute("patientList", patientRepository.findAll());
        modelMap.addAttribute("doctorList", doctorRepository.findAll());
        return "addAppointment";
    }

    @PostMapping("/add")
    public String addAppoPost(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String deleteAppointment(@RequestParam("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
