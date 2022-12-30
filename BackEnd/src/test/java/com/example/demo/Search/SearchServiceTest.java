package com.example.demo.Search;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.DoctorSpeciality;
import com.example.demo.Repo.DoctorRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {SearchService.class})
@ExtendWith(SpringExtension.class)
class SearchServiceTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @Mock
    private Doctor doctor;

    @Autowired
    private SearchService searchService;

    @Test
    void testGetDoctorBySpecializationWhenFound() {
        ArrayList<Doctor> expectedDoctorList = new ArrayList<>();
        doctor.setSpecialization(DoctorSpeciality.Family);
        expectedDoctorList.add(doctor);
        when(doctorRepository.getDoctorBySpecialization(1)).thenReturn(expectedDoctorList);
        List<Doctor> actualDoctorBySpecialization = searchService.getDoctorBySpecialization(1);
        assertSame(expectedDoctorList, actualDoctorBySpecialization);
        assertEquals(1, actualDoctorBySpecialization.size());
        verify(doctorRepository).getDoctorBySpecialization(1);
    }

    @Test
    void testGetDoctorBySpecializationWhenEmpty() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        when(doctorRepository.getDoctorBySpecialization(anyInt())).thenReturn(doctorList);
        List<Doctor> actualDoctorBySpecialization = searchService.getDoctorBySpecialization(1);
        assertSame(doctorList, actualDoctorBySpecialization);
        assertTrue(actualDoctorBySpecialization.isEmpty());
        verify(doctorRepository).getDoctorBySpecialization(1);
    }

    @Test
    void testGetDoctorByNameWhenFound() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Saad", DoctorSpeciality.Family, 1,
                "42 Main Street Cairo", 33, 200, 100,
                "saad@gmail.com", "password"));
        when(doctorRepository.findAll()).thenReturn(doctorList);
        assertEquals(1, searchService.getDoctorByName("Saad").size());
        verify(doctorRepository).findAll();
    }

    @Test
    void testGetDoctorByNameWhenEmpty() {
        when(doctorRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(searchService.getDoctorByName("Name").isEmpty());
        verify(doctorRepository).findAll();
    }

    @Test
    void testGetDoctorByNameWhenNotFound() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        doctorList.add(new Doctor("Saad", DoctorSpeciality.Family, 1,
                "42 Main Street Cairo", 33, 200, 100,
                "saad@gmail.com", "password"));
        when(doctorRepository.findAll()).thenReturn(doctorList);
        assertTrue(searchService.getDoctorByName("Salah").isEmpty());
        verify(doctorRepository).findAll();
    }
}
