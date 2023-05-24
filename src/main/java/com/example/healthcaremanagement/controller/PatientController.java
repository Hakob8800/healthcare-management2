package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {


    private final PatientRepository patientRepository;

    @GetMapping
    public String allPatients(ModelMap modelMap) {
        modelMap.addAttribute("patientList", patientRepository.findAll());
        return "allPatients";
    }

    @GetMapping("/add")
    public String addPatientGet() {
        return "addPatient";
    }

    @PostMapping("/add")
    public String addPatientPost(@ModelAttribute Patient patient) {
        patientRepository.save(patient);
        return "redirect:/patients";
    }

    @GetMapping("/remove")
    public String deletePatient(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patients";
    }
}
