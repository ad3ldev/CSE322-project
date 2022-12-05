package com.example.demo.Service;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Models.Doctor;
import com.example.demo.Repo.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void addDoctor(Doctor doctor) {
        Boolean emailExist = doctorRepository.isEmailExist(doctor.getEmail());
        if(emailExist) {
            throw new BadRequestException("Email " + doctor.getEmail() + " is taken");
        }
        doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long doctorId) {
        if(!doctorRepository.existsById(doctorId)) {
            throw new UserNotFoundException("Doctor with id " + doctorId + " does not exist");
        }
        doctorRepository.deleteById(doctorId);
    }
}
