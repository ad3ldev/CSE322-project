package com.example.demo.Utils;

import com.example.demo.Models.Appointment;

public class Appointment_Result {
    State state ;
    String description;
    Appointment appointment;

    public Appointment_Result(State state, String description, Appointment appointment) {
        this.state = state;
        this.description = description;
        this.appointment = appointment;
    }

    public State getState() {
        return state;
    }

    public String getDescription() {
        return description;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
