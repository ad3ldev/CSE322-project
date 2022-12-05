package com.example.demo.Service;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Exception.UserNotFoundException;
import com.example.demo.Models.Doctor;
import com.example.demo.Models.DoctorSpeciality;
import com.example.demo.Repo.DoctorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;
    private DoctorService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DoctorService(doctorRepository);
    }

    @Test
    void canGetAllDoctors() {
        //when
        underTest.getAllDoctors();
        //then
        verify(doctorRepository).findAll();
    }

    @Test
    void canAddDoctor() {
        // given
        Doctor doctor = new Doctor(
                "Ahmad",
                DoctorSpeciality.Oncologist,
                5,
                "Alex",
                31,
                200,
                500,
                "Ahmad55@gmail.com",
                "ahmad55"
        );
        // when
        underTest.addDoctor(doctor);
        // then
        ArgumentCaptor<Doctor> doctorArgumentCaptor =
                ArgumentCaptor.forClass(Doctor.class);
        verify(doctorRepository).save(doctorArgumentCaptor.capture());
        Doctor capturedDoctor = doctorArgumentCaptor.getValue();
        assertThat(capturedDoctor).isEqualTo(doctor);
    }

    @Test
    void willThrowEmailIsTakenWhen() {
        // given
        Doctor doctor = new Doctor(
                "Ahmad",
                DoctorSpeciality.Oncologist,
                5,
                "Alex",
                31,
                200,
                500,
                "Ahmad55@gmail.com",
                "ahmad55"
        );
        given(doctorRepository.isEmailExist(anyString())).willReturn(true);
        // when
        // then
        assertThatThrownBy(() -> underTest.addDoctor(doctor))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Email " + doctor.getEmail() + " is taken");
        verify(doctorRepository, never()).save(any());
    }

    @Test
    void canDeleteDoctor() {
        // given
        long id = 10;
        given(doctorRepository.existsById(id)).willReturn(true);
        // when
        underTest.deleteDoctor(id);
        // then
        verify(doctorRepository).deleteById(id);
    }

    @Test
    void willThrowDoctorNotFoundWhenDelete() {
        // given
        long id = 10;
        given(doctorRepository.existsById(id)).willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> underTest.deleteDoctor(id))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("Doctor with id " + id + " does not exist");
        verify(doctorRepository, never()).deleteById(any());
    }
}
