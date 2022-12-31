package com.example.demo.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.example.demo.Models.Appointment;
import org.junit.jupiter.api.Test;

class AppointmentResultTest {
    @Test
    void testConstructor() {
        Appointment appointment = new Appointment();
        Appointment_Result actualAppointmentResult = new Appointment_Result(State.SUCCESS,
                "The Appointment is taken", appointment);
        assertSame(appointment, actualAppointmentResult.getAppointment());
        assertEquals("The Appointment is taken", actualAppointmentResult.getDescription());
        assertEquals(State.SUCCESS, actualAppointmentResult.getState());
    }

    @Test
    void testSettersAndGetters() {
        Appointment_Result actualAppointmentResult = new Appointment_Result(null, null, null);
        Appointment appointment = new Appointment();
        actualAppointmentResult.setAppointment(appointment);
        actualAppointmentResult.setDescription("The Appointment is taken");
        actualAppointmentResult.setState(State.FAILURE);
        assertSame(appointment, actualAppointmentResult.getAppointment());
        assertEquals("The Appointment is taken", actualAppointmentResult.getDescription());
        assertEquals(State.FAILURE, actualAppointmentResult.getState());
    }
}
