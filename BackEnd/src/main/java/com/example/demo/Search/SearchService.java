package com.example.demo.Search;

import com.example.demo.Models.Doctor;
import com.example.demo.Repo.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class SearchService {
    private final DoctorRepository dr;

    public List<Doctor> getDoctorBySpecialization(int specialization){
        return dr.getDoctorBySpecialization(specialization);
    }
    public List<Doctor> getDoctorByName(String name){
        List<Doctor> all_Doctors = dr.findAll();
        ArrayList<Doctor> returned = new ArrayList<>();
        for(var doctor: all_Doctors){
            if(doctor.getName().contains(name))
                returned.add(doctor);
        }
        return returned;
    }


}
