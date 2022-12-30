package com.example.demo.Utils;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class ResultTest {
    @Test
    void testConstructorWithStateSuccess() {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Result actualResult = new Result(State.SUCCESS, 200L, Type.Patient, patient, doctor);
        assertSame(doctor, actualResult.getDoctor());
        assertEquals(200L, actualResult.getId().longValue());
        assertSame(patient, actualResult.getPatient());
        assertEquals(State.SUCCESS, actualResult.getState());
        assertEquals(Type.Patient, actualResult.getType());
    }

    @Test
    void testSettersAndGettersWithStateFailure() {
        Patient patient = new Patient();
        Doctor doctor = new Doctor();
        Result actualResult = new Result(null, null, null, null, null);
        actualResult.setDoctor(doctor);
        actualResult.setId(500L);
        actualResult.setPatient(patient);
        actualResult.setState(State.FAILURE);
        actualResult.setType(Type.Doctor);
        assertSame(doctor, actualResult.getDoctor());
        assertEquals(500L, actualResult.getId().longValue());
        assertSame(patient, actualResult.getPatient());
        assertEquals(State.FAILURE, actualResult.getState());
        assertEquals(Type.Doctor, actualResult.getType());
    }
}
