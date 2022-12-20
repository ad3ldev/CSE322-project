package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@Embeddable
public class AppointmentPrimaryData implements Serializable {
    @Column
    private long doctorId;
    @Column
    private long patientId;

    @Column
    private Date date;

    @Column
    private Time startTime;

    @Column
    private Time endTime;
    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    public long getPatientId() {
        return patientId;
    }

    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public AppointmentPrimaryData(long doctorId, long patientId, Date date, Time startTime, Time endTime) {
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
