package com.example.demo.Utils;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentStatus;
import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {JsonCustomMapper.class})
@ExtendWith(SpringExtension.class)
class JsonCustomMapperTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private JsonCustomMapper jsonCustomMapper;

    @MockBean
    private PatientRepository patientRepository;

    @Test
    void testJsonToAppointment() throws JSONException, JsonProcessingException {
        String json = "" +
                "{ " +
                "    \"doctorId\":2," +
                "    \"patientId\":1, " +
                "    \"date\":\"2012-04-20\"," +
                "    \"period\":\"12-13\",   " +
                "    \"price\":\"200.0\"" +
                "}";
        List<Doctor> doctorList = new ArrayList<>();
        Doctor doctor = new Doctor();
        doctorList.add(doctor);
        List<Patient> patientList = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(5L);
        patientList.add(patient);
        given(doctorRepository.findAllById(any())).willReturn(doctorList);
        given(patientRepository.findAllById(any())).willReturn(patientList);
        Appointment actualAppointment = jsonCustomMapper.jsonToAppointment(json);
        assertEquals(0L, actualAppointment.getDoctorId());
        assertEquals(5L, actualAppointment.getPatientId());
        assertEquals(Date.valueOf("2012-04-20"), actualAppointment.getDate());
        assertEquals(Time.valueOf("12:00:00"), actualAppointment.getStartTime());
        assertEquals(Time.valueOf("13:00:00"), actualAppointment.getEndTime());
        assertEquals(200.0, actualAppointment.getPrice());
        assertEquals(AppointmentStatus.IN_PROGRESS, actualAppointment.getStatus());
    }
}
