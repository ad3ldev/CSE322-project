package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@Entity
@Table
public class Appointment {
    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private AppointmentStatus status;

    @EmbeddedId
    private AppointmentPrimaryData appointmentPrimaryData;

    public AppointmentPrimaryData getAppointmentPrimaryData() {
        return appointmentPrimaryData;
    }

    public void setAppointmentPrimaryData(AppointmentPrimaryData appointmentPrimaryData) {
        this.appointmentPrimaryData = appointmentPrimaryData;
    }

    public long getDoctorId() {
        return this.appointmentPrimaryData.getDoctorId();
    }

    public void setDoctorId(long doctorId) {
        this.appointmentPrimaryData.setDoctorId(doctorId);
    }

    public long getPatientId() {
        return this.appointmentPrimaryData.getPatientId();
    }

    public void setPatient(long patientId) {
        this.appointmentPrimaryData.setPatientId(patientId);
    }

    public Date getDate() {
        return this.appointmentPrimaryData.getDate();
    }

    public void setDate(Date date) {
        this.appointmentPrimaryData.setDate(date);
    }

    public Time getStartTime() {
        return this.appointmentPrimaryData.getStartTime();
    }

    public void setStartTime(Time startTime) {
        this.appointmentPrimaryData.setStartTime(startTime);
    }

    public Time getEndTime() {
        return this.appointmentPrimaryData.getEndTime();
    }

    public void setEndTime(Time endTime) {
        this.appointmentPrimaryData.setEndTime(endTime);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Appointment(Doctor doctor, Patient patient, Date date,
                       Time startTime, Time endTime, double price, AppointmentStatus status) {
        this.appointmentPrimaryData = new AppointmentPrimaryData(doctor.getId(),
                                      patient.getId(), date, startTime, endTime);
        this.price = price;
        this.status = status;
    }
}
