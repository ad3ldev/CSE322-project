package com.example.demo.Service;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.DoctorSpeciality;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ContextConfiguration(classes = {ProfileCreationService.class})
@ExtendWith(SpringExtension.class)
class ProfileCreationServiceTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private ProfileCreationService profileCreationService;

    @Test
    void testCreateProfileWithPatient() throws JsonProcessingException, JSONException {
        String json = "{" +
                "    \"type\":\"Patient\"," +
                "    \"name\":\"Mario\", " +
                "    \"email\": \"sa@gmail.com\"," +
                "    \"password\":\"33833939393\", " +
                "    \"age\":15," +
                "    \"previousReports\":\"hi\"," +
                "    \"gender\":\"M\"," +
                "    \"address\":\"somePlace\"" +
                "}";
        Result result = profileCreationService.createProfile(json);
        assertNotNull(result);
        assertEquals(State.SUCCESS, result.getState());
        assertEquals(0, result.getId());
        assertEquals(Type.Patient, result.getType());
        assertNull(result.getDoctor());
        Patient patient = result.getPatient();
        assertEquals("somePlace", patient.getAddress());
        assertEquals(15, patient.getAge());
        assertEquals("sa@gmail.com", patient.getEmail());
        assertEquals("M", patient.getGender());
        assertEquals(0L, patient.getId());
        assertEquals("Mario", patient.getName());
        assertEquals("33833939393", patient.getPassword());
        assertEquals("hi", patient.getPreviousReports());
        assertEquals(Type.Patient, patient.getType());
    }

    @Test
    void testCannotCreateProfileWithPatient() throws JsonProcessingException, JSONException {
        String json = "{" +
                "    \"type\":\"Patient\"," +
                "    \"name\":\"Mario\", " +
                "    \"email\": \"sa@gmail.com\"," +
                "    \"password\":\"33833939393\", " +
                "    \"age\":15," +
                "    \"previousReports\":\"hi\"," +
                "    \"gender\":\"M\"," +
                "    \"address\":\"somePlace\"" +
                "}";
        given(patientRepository.isEmailExist(any())).willReturn(true);
        Result actualResult = profileCreationService.createProfile(json);
        assertEquals(State.FAILURE, actualResult.getState());
        assertNull(actualResult.getDoctor());
        assertNull(actualResult.getType());
        assertNull(actualResult.getId());
        assertNull(actualResult.getPatient());
    }

    @Test
    void testCreateProfileWithDoctor() throws JsonProcessingException, JSONException {
        String json = "{" +
                "    \"type\": \"Doctor\"," +
                "    \"name\": \"Mario\"," +
                "    \"email\": \"dr@gmail.com\"," +
                "    \"password\": \"33833939393\"," +
                "    \"specialization\": \"Family\"," +
                "    \"yearsOfExperience\": 10," +
                "    \"consultationPrice\": 100," +
                "    \"followUpPrice\": 50," +
                "    \"address\": \"somePlace\"" +
                "}";
        Result result = profileCreationService.createProfile(json);
        assertNotNull(result);
        assertEquals(State.SUCCESS, result.getState());
        assertEquals(0, result.getId());
        assertEquals(Type.Doctor, result.getType());
        assertNull(result.getPatient());

        Doctor doctor = result.getDoctor();
        assertEquals("somePlace", doctor.getAddress());
        assertEquals(10, doctor.getYearsOfExperience());
        assertEquals(Type.Doctor, doctor.getType());
        assertEquals(DoctorSpeciality.Family, doctor.getSpecialization());
        assertEquals("33833939393", doctor.getPassword());
        assertEquals("Mario", doctor.getName());
        assertEquals(50, doctor.getFollowUpPrice());
        assertEquals("dr@gmail.com", doctor.getEmail());
        assertEquals(100, doctor.getConsultationPrice());
        assertEquals(0L, (new Doctor()).getId());
    }

    @Test
    void testCannotCreateProfileWithDoctor() throws JsonProcessingException, JSONException {
        String json = "{" +
                "    \"type\": \"Doctor\"," +
                "    \"name\": \"Mario\"," +
                "    \"email\": \"dr@gmail.com\"," +
                "    \"password\": \"33833939393\"," +
                "    \"specialization\": \"Family\"," +
                "    \"yearsOfExperience\": 1000," +
                "    \"consultationPrice\": 100," +
                "    \"followUpPrice\": 1," +
                "    \"address\": \"somePlace\"" +
                "}";
        given(doctorRepository.isEmailExist(any())).willReturn(true);
        Result actualResult = profileCreationService.createProfile(json);
        assertEquals(State.FAILURE, actualResult.getState());
        assertNull(actualResult.getDoctor());
        assertNull(actualResult.getType());
        assertNull(actualResult.getId());
        assertNull(actualResult.getPatient());
    }

    @Test
    void testFailResult() throws JsonProcessingException, JSONException {
        String json = "{" +
                "    \"type\": \"type\"," +
                "    \"name\": \"Mario\"," +
                "    \"email\": \"dr@gmail.com\"," +
                "    \"password\": \"33833939393\"," +
                "    \"specialization\": \"Family\"," +
                "    \"yearsOfExperience\": 1000," +
                "    \"consultationPrice\": 100," +
                "    \"followUpPrice\": 1," +
                "    \"address\": \"somePlace\"" +
                "}";
        Result actualResult = profileCreationService.createProfile(json);
        assertEquals(State.FAILURE, actualResult.getState());
        assertNull(actualResult.getDoctor());
        assertNull(actualResult.getType());
        assertNull(actualResult.getId());
        assertNull(actualResult.getPatient());
    }
}
