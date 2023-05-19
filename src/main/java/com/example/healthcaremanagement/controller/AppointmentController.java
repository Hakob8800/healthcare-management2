package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    private @Autowired
    DoctorRepository doctorRepository;

    @GetMapping
    public String allAppointments(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("appointmentList", appointmentRepository.findAll());
        modelMap.addAttribute("user",currentUser.getUser());
        return "allAppointments";
    }

    @GetMapping("/add")
    public String addAppoGet(ModelMap modelMap) {
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
