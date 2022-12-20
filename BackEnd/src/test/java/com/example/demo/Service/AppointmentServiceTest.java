package com.example.demo.Service;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Models.*;
import com.example.demo.Repo.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepository;
    private AppointmentService underTest;

    @BeforeEach
    void setUp() {
        underTest = new AppointmentService(appointmentRepository);
    }

    @Test
    void shouldGetAllAppointments() {
        // when
        underTest.getAllAppointments();
        // then
        verify(appointmentRepository).findAll();
    }

    @Test
    void shouldGetAllDoctorAppointments() {
        Doctor doctor = new Doctor(
                "Ahmad Hassan",
                DoctorSpeciality.Family,
                1, "Alex Sidi Gaber",
                30,
                200,
                300,
                "Ahmadpro77@gmail.com",
                "ahmad77#@14");

        Patient patient = new Patient();
        patient.setAddress("street 45");
        patient.setAge(26);
        patient.setEmail("ahmad@gmail.com");
        patient.setGender("M");
        patient.setId(5L);
        patient.setName("ahmad");
        patient.setPassword("ahmadPro");
        patient.setPreviousReports("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");

        Appointment appointment = new Appointment(doctor,
                patient, Date.valueOf("2022-12-20"),
                Time.valueOf("11:45:00"),
                Time.valueOf("14:30:00"),
                100.0, AppointmentStatus.IN_PROGRESS);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        // when
        given(appointmentRepository.findAll())
                .willReturn(appointments);
        List<Appointment> expected = underTest.getDoctorAppointments(doctor.getId());
        // then
        assertEquals(1, expected.size());
    }

    @Test
    void shouldGetAllPatientAppointments() {
        Doctor doctor = new Doctor(
                "Ahmad Hassan",
                DoctorSpeciality.Family,
                1, "Alex Sidi Gaber",
                30,
                200,
                300,
                "Ahmadpro77@gmail.com",
                "ahmad77#@14");

        Patient patient = new Patient();
        patient.setAddress("street 45");
        patient.setAge(26);
        patient.setEmail("ahmad@gmail.com");
        patient.setGender("M");
        patient.setId(5L);
        patient.setName("ahmad");
        patient.setPassword("ahmadPro");
        patient.setPreviousReports("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");

        Appointment appointment = new Appointment(doctor,
                patient, Date.valueOf("2022-12-20"),
                Time.valueOf("11:45:00"),
                Time.valueOf("14:30:00"),
                100.0, AppointmentStatus.IN_PROGRESS);
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(appointment);
        // when
        given(appointmentRepository.findAll()).willReturn(appointments);
        List<Appointment> expected = underTest.getPatientAppointments(patient.getId());
        // then
        assertEquals(1, expected.size());
    }

    @Test
    void shouldAddAppointment() {
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), Date.valueOf("2020-11-03"),
                Time.valueOf("20:30:00"), Time.valueOf("21:00:00"),
                200.0, AppointmentStatus.IN_PROGRESS);
        underTest.addAppointment(appointment);
        ArgumentCaptor<Appointment> appointmentArgumentCaptor =
                ArgumentCaptor.forClass(Appointment.class);
        verify(appointmentRepository).save(appointmentArgumentCaptor.capture());
        Appointment capturedAppointment = appointmentArgumentCaptor.getValue();
        assertThat(capturedAppointment).isEqualTo(appointment);
    }

    @Test
    void shouldNotAddAppointment() {
        Appointment appointment = new Appointment(mock(Doctor.class), mock(Patient.class),
                Date.valueOf("2020-11-03"), Time.valueOf("20:30:00"), Time.valueOf("21:00:00"),
                200.0, AppointmentStatus.IN_PROGRESS);
        given(appointmentRepository.existsById(appointment.getAppointmentPrimaryData()))
                .willReturn(true);
        assertThatThrownBy(() -> underTest.addAppointment(appointment))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Appointment is taken");
        verify(appointmentRepository, never()).save(any());
    }

    @Test
    void shouldDeleteAppointment() {
        // given
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), Date.valueOf("2022-12-20"),
                Time.valueOf("11:45:00"),
                Time.valueOf("14:30:00"),
                100.0, AppointmentStatus.IN_PROGRESS);
        AppointmentPrimaryData appointmentPrimaryData = appointment.getAppointmentPrimaryData();
        given(appointmentRepository.existsById(appointmentPrimaryData)).willReturn(true);
        // when
        underTest.deleteAppointment(appointment);
        // then
        verify(appointmentRepository).deleteById(appointmentPrimaryData);
    }

    @Test
    void shouldNotDeleteAppointment() {
        // given
        Appointment appointment = new Appointment(mock(Doctor.class),
                mock(Patient.class), Date.valueOf("2022-12-20"),
                Time.valueOf("11:45:00"),
                Time.valueOf("14:30:00"),
                100.0, AppointmentStatus.IN_PROGRESS);
        AppointmentPrimaryData appointmentPrimaryData = appointment.getAppointmentPrimaryData();
        given(appointmentRepository.existsById(appointmentPrimaryData)).willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteAppointment(appointment))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Appointment is not Exist!");

        verify(appointmentRepository, never()).deleteById(any());
    }
}
