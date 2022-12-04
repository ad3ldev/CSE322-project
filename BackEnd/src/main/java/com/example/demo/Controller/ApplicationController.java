package com.example.demo.Controller;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
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
    PatientRepository pr;
    @Autowired
    DoctorRepository dr;

    @PostMapping("/signUp")
    public Result signUp(@RequestBody String userJsonString) throws JsonProcessingException, JSONException {
        System.out.println("signUp");
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

    @GetMapping("/login")
    public Result login(@RequestBody String infoString) throws JSONException {
    System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return authService.Login(infoString);
    }

}
