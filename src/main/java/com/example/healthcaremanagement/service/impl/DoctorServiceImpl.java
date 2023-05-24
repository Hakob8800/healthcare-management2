package com.example.healthcaremanagement.service.impl;

import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    @Value("${healthcare-management.uploaded-pictures.folder.path}")
    private String uploadedFolderPath;


    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public void savePicture(MultipartFile multipartFile, Doctor doctor) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.nanoTime() + "_" + multipartFile.getOriginalFilename();
            File file = new File(uploadedFolderPath + fileName);
            multipartFile.transferTo(file);
            doctor.setProfilePic(fileName);
        }
    }

    @Override
    public void saveDoctor(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteById(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor findById(int id) {
        Optional<Doctor> byId = doctorRepository.findById(id);
        return byId.orElse(null);
    }
}
