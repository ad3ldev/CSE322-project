package com.example.demo.Service;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {AuthenticationService.class})
@ExtendWith(SpringExtension.class)
class AuthenticationServiceTest {
    @Autowired
    private AuthenticationService authenticationService;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void testLoginWithPatient() throws JSONException {
        String json = "{" +
                "    \"email\": \"mosalah@gmail.com\"," +
                "    \"password\": \"Me$$iIsTheGo@t\"," +
                "    \"type\": \"Patient\"" +
                "}";
        List<Long> ids = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient());
        ids.add(0L);
        given(patientRepository.findPatientByEmailAndPassword(anyString(), anyString())).willReturn(ids);
        given(patientRepository.findAllById(any())).willReturn(patients);
        Result actualResult = authenticationService.Login(json);
        assertNotNull(actualResult);
        assertEquals(State.SUCCESS, actualResult.getState());
        assertEquals(0L, actualResult.getId());
        assertEquals(Type.Patient, actualResult.getType());
        assertNull(actualResult.getDoctor());
    }

    @Test
    void testCannotLoginWithPatient() throws JSONException {
        String json = "{" +
                "    \"email\": \"ahmadSalem77@gmail.com\"," +
                "    \"password\": \"ahmadpro#&*55\"," +
                "    \"type\": \"Patient\"" +
                "}";
        Result actualResult = authenticationService.Login(json);
        assertNotNull(actualResult);
        assertEquals(State.FAILURE, actualResult.getState());
        assertNull(actualResult.getId());
        assertNull(actualResult.getPatient());
        assertNull(actualResult.getType());
        assertNull(actualResult.getDoctor());
    }

    @Test
    void testLoginWithDoctor() throws JSONException {
        String json = "{" +
                "    \"email\": \"DrSaleh@gmail.com\"," +
                "    \"password\": \"DrSaleh512#medicine\"," +
                "    \"type\": \"Doctor\"" +
                "}";
        List<Long> ids = new ArrayList<>();
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor());
        ids.add(5L);
        given(doctorRepository.findDoctorByEmailAndPassword(anyString(), anyString())).willReturn(ids);
        given(doctorRepository.findAllById(any())).willReturn(doctors);
        Result actualResult = authenticationService.Login(json);
        assertNotNull(actualResult);
        assertEquals(State.SUCCESS, actualResult.getState());
        assertEquals(5L, actualResult.getId());
        assertNull(actualResult.getPatient());
        assertEquals(Type.Doctor, actualResult.getType());
    }

    @Test
    void testCannotLoginWithDoctor() throws JSONException {
        String json = "{" +
                "    \"email\": \"ahmaddoctor@gmail.com\"," +
                "    \"password\": \"ahmaddoctor\"," +
                "    \"type\": \"Doctor\"" +
                "}";
        Result actualResult = authenticationService.Login(json);
        assertNotNull(actualResult);
        assertEquals(State.FAILURE, actualResult.getState());
        assertNull(actualResult.getId());
        assertNull(actualResult.getPatient());
        assertNull(actualResult.getType());
        assertNull(actualResult.getDoctor());
    }
}
