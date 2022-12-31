package com.example.demo.Controller;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.Doctor;
import com.example.demo.Models.DoctorSpeciality;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Search.SearchService;
import com.example.demo.Service.AppointmentService;
import com.example.demo.Service.AuthenticationService;
import com.example.demo.Service.ProfileCreationService;
import com.example.demo.Utils.Appointment_Result;
import com.example.demo.Utils.JsonCustomMapper;
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
    AppointmentService appointmentService;
    @Autowired
    SearchService searchService;

    @Autowired
    PatientRepository pr;
    @Autowired
    DoctorRepository dr;

    @Autowired
    JsonCustomMapper jsonCustomMapper;


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
    public List<Doctor> getDoctorsBySpecialization(@RequestBody String specialization){
        specialization = specialization.replace("=", "");
        var listOfDoctors =  searchService.getDoctorBySpecialization(DoctorSpeciality.valueOf(specialization).ordinal());
        return listOfDoctors;
    }

    @PostMapping("/searchByName")
    public List<Doctor> getDoctorByName(@RequestBody String name){
        return searchService.getDoctorByName(name);
    }

    @PostMapping("/makeAppointment")
    public Appointment_Result makeAppointment(@RequestBody String json) throws JSONException, JsonProcessingException {
        return appointmentService.addAppointment(jsonCustomMapper.jsonToAppointment(json));
    }
    @PostMapping("/cancelAppointment")
    public Appointment_Result cancelApppointment(@RequestBody String json) throws JSONException, JsonProcessingException {
        return appointmentService.deleteAppointment(jsonCustomMapper.cancelJsonToAppointment(json));
    }
    
    @PostMapping("/getPatientAppointments")
    public List<Appointment> getPatientAppointments(@RequestBody String id){
        id = id.replace("=", "");
        return appointmentService.getPatientAppointments(Integer.parseInt(id));
    }

    @PostMapping("/getDoctorAppointments")
    public List<Appointment> getDoctorAppointments(@RequestBody String id){
        id = id.replace("=", "");
        return appointmentService.getDoctorAppointments(Integer.parseInt(id));
    }

    @PostMapping("/confirmAppointment")
    public void ConfirmAppointment(@RequestBody String json) throws JSONException, JsonProcessingException {
        appointmentService.confirmAppointment(jsonCustomMapper.getPrimaryData_with_confirm(json),
                jsonCustomMapper.getDoctorComment(json));
    }


}
