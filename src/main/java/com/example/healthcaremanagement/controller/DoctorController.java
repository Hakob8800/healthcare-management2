package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("")
    public String allDoctors(ModelMap modelMap) {
        modelMap.addAttribute("doctorList", doctorService.findAll());
        return "allDoctors";
    }

    @GetMapping("/add")
    public String addDoctorForm() {
        return "addDoctor";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor,
                            @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        doctorService.savePicture(multipartFile, doctor);
        doctorService.saveDoctor(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/remove")
    public String removeDoctor(@RequestParam("id") int id) {
        doctorService.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String singleDoctorPage(@PathVariable("id") int id, ModelMap modelMap) {
        Doctor doctor = doctorService.findById(id);
        if (doctor != null) {
            modelMap.addAttribute("doctor", doctor);
            return "singleDoctor";
        }
        return "redirect:/doctors";
    }
}
