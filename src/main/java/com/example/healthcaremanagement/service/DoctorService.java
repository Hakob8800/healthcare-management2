package com.example.healthcaremanagement.service;

import com.example.healthcaremanagement.entity.Doctor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface DoctorService {

    List<Doctor> findAll();

    void savePicture(MultipartFile multipartFile, Doctor doctor) throws IOException;

    void saveDoctor(Doctor doctor);

    void deleteById(int id);

    Doctor findById(int id);
}
