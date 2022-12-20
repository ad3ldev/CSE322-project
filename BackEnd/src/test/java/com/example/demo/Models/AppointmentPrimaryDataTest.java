package com.example.demo.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

class AppointmentPrimaryDataTest {
    @Test
    void testConstructor() {
        AppointmentPrimaryData appointmentPrimaryData = new AppointmentPrimaryData(
                10, 2, Date.valueOf("2000-05-11"),
                Time.valueOf("22:30:00"), Time.valueOf("23:00:00"));

        assertEquals(10, appointmentPrimaryData.getDoctorId());
        assertEquals(2, appointmentPrimaryData.getPatientId());
        assertEquals(Date.valueOf("2000-05-11"), appointmentPrimaryData.getDate());
        assertEquals(Time.valueOf("22:30:00"), appointmentPrimaryData.getStartTime());
        assertEquals(Time.valueOf("23:00:00"), appointmentPrimaryData.getEndTime());
    }

    @Test
    void testConstructor2() {
        AppointmentPrimaryData appointmentPrimaryData = new AppointmentPrimaryData();
        assertNotNull(appointmentPrimaryData);
        appointmentPrimaryData.setDoctorId(10);
        appointmentPrimaryData.setPatientId(2);
        appointmentPrimaryData.setDate(Date.valueOf("2000-05-11"));
        appointmentPrimaryData.setStartTime(Time.valueOf("22:30:00"));
        appointmentPrimaryData.setEndTime(Time.valueOf("23:00:00"));

        assertEquals(10, appointmentPrimaryData.getDoctorId());
        assertEquals(2, appointmentPrimaryData.getPatientId());
        assertEquals(Date.valueOf("2000-05-11"), appointmentPrimaryData.getDate());
        assertEquals(Time.valueOf("22:30:00"), appointmentPrimaryData.getStartTime());
        assertEquals(Time.valueOf("23:00:00"), appointmentPrimaryData.getEndTime());
    }
}

