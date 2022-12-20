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
}
