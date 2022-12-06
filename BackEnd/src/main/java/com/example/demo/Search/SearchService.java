package com.example.demo.Search;

import com.example.demo.Models.Doctor;
import com.example.demo.Repo.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class SearchService {
    private final DoctorRepository dr;

    public List<Doctor> getDoctorBySpecializaiton(String specialization){
        return dr.getDoctorBySpecialization(specialization);
    }

}
