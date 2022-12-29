package com.example.demo.Utils;


import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;

public class Result {
    State state;
    Long id;
    Patient patient;
    Doctor doctor;
    Type type;


    public Result(State state, Long id, Type type, Patient patient, Doctor doctor) {
        this.state = state;
        this.id = id;
        this.type = type;
        this.patient = patient;
        this.doctor = doctor;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public State getState() {
        return state;
    }

    //Notice state=0 failure
    //state = 1 success

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }


    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }







}
