package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.User;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@Controller
@RequestMapping("/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;
    @Value("${healthcare-management.uploaded-pictures.folder.path}")
    private String uploadedFolderPath;

    @GetMapping("")
    public String allDoctors(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("user",currentUser.getUser());
        modelMap.addAttribute("doctorList", doctorRepository.findAll());
        return "allDoctors";
    }

    @GetMapping("/add")
    public String addDoctorForm() {
        return "addDoctor";
    }

    @PostMapping("/add")
    public String addDoctor(@ModelAttribute Doctor doctor,
                            @RequestParam("picture") MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadedFolderPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
        doctorRepository.save(doctor);
        return "redirect:/doctors";
    }

    @GetMapping("/remove")
    public String removeDoctor(@RequestParam("id") int id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String singleDoctorPage(@PathVariable("id") int id, ModelMap modelMap) {
        Optional<Doctor> byId = doctorRepository.findById(id);
        if (byId.isPresent()) {
            Doctor doctor = byId.get();
            modelMap.addAttribute("doctor", doctor);
            return "singleDoctor";
        } else {
            return "redirect:/doctors";
        }

    }
}
