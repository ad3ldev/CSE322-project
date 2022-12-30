package com.example.demo.Models;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Time;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class AppointmentTest {
    @Test
    void testConstructor() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), Date.valueOf("2022-12-20"),
                Time.valueOf("11:45:00"),
                Time.valueOf("14:30:00"),
                100.0, AppointmentStatus.IN_PROGRESS);
        assertEquals(100.0, appointment.getPrice());
        assertEquals(AppointmentStatus.IN_PROGRESS, appointment.getStatus());
        assertEquals(Date.valueOf("2022-12-20"), appointment.getDate());
        assertEquals(Time.valueOf("11:45:00"), appointment.getStartTime());
        assertEquals(Time.valueOf("14:30:00"), appointment.getEndTime());
    }

    @Test
    void testSettersAndGetters() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), null, null, null, 0, null);
        appointment.setDoctorId(20);
        appointment.setPatientId(55);
        appointment.setDate(Date.valueOf("2022-12-20"));
        appointment.setStartTime(Time.valueOf("11:45:00"));
        appointment.setEndTime(Time.valueOf("14:30:00"));
        appointment.setStatus(AppointmentStatus.IN_PROGRESS);
        appointment.setPrice(100.0);
        assertEquals(100.0, appointment.getPrice());
        assertEquals(AppointmentStatus.IN_PROGRESS, appointment.getStatus());
        assertEquals(Date.valueOf("2022-12-20"), appointment.getDate());
        assertEquals(Time.valueOf("11:45:00"), appointment.getStartTime());
        assertEquals(Time.valueOf("14:30:00"), appointment.getEndTime());
        assertEquals(20, appointment.getDoctorId());
        assertEquals(55, appointment.getPatientId());
        assertNotNull(appointment.getAppointmentPrimaryData());
    }

    @Test
    void testNoArgsConstructor() {
        Appointment appointment = new Appointment();
        assertNotNull(appointment);
    }

    @Test
    void testAppointmentPrimaryData() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), null, null, null, 0, null);
        appointment.setAppointmentPrimaryData(mock(AppointmentPrimaryData.class));
        assertNotNull(appointment.getAppointmentPrimaryData());
    }

    @Test
    void testDoctorCommentsWhenFound() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), null, null, null, 0, null);
        appointment.setAppointmentPrimaryData(mock(AppointmentPrimaryData.class));
        appointment.setDoctorComments("The state of the patient is good.");
        assertEquals("The state of the patient is good.", appointment.getDoctorComments());
    }

    @Test
    void testDoctorCommentsWhenNotFound() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), null, null, null, 0, null);
        appointment.setAppointmentPrimaryData(mock(AppointmentPrimaryData.class));
        assertNull(appointment.getDoctorComments());
    }
}
