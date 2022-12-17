package com.example.demo.Controller;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Search.SearchService;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.Service.ProfileCreationService;
import com.example.demo.Utils.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ApplicationController {

    @Autowired
    AuthenticationService authService;
    @Autowired
    ProfileCreationService profileCreationService;

    @Autowired
    SearchService searchService;

    @Autowired
    PatientRepository pr;
    @Autowired
    DoctorRepository dr;

    @PostMapping("/signUp")
    public Result signUp(@RequestBody String userJsonString) throws JsonProcessingException, JSONException {
        return profileCreationService.createProfile(userJsonString);
    }

    @GetMapping("/getDoctors")
    public List<Doctor> displayDoctors(){
        return dr.findAll();
    }

    @GetMapping("/getPatients")
    public List<Patient> displayPatients(){
        return pr.findAll();
    }

    @PostMapping("/login")
    public Result login(@RequestBody String infoString) throws JSONException {
        var x = authService.Login(infoString);
        System.out.println(x.getDoctor());
        System.out.println(x.getId());
        System.out.println(x.getType());
        return x;
    }


    @PostMapping("/searchBySpecialization")
    public List<Doctor> getDoctorsBySpecialization(@RequestBody String Specialization){
        return searchService.getDoctorBySpecializaiton(Specialization);
    }
    
    @PostMapping("/makeAppointment")
    public Appointment_Result makeAppointment(@RequestBody Appointment appointment){
        return appointmentService.addAppointment(appointment);
    }
    @PostMapping("/cancelAppointment")
    public Appointment_Result cancelApppointment(@RequestBody Appointment appointment){
        return appointmentService.deleteAppointment(appointment);
    }

}
