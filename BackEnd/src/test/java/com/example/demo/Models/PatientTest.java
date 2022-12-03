package com.example.AnotherDemo.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientTest {
    @Test
    void testPatientConstructor() {
        Patient actualPatient = new Patient();
        actualPatient.setAddress("street 45");
        actualPatient.setAge(26);
        actualPatient.setEmail("ahmad@gmail.com");
        actualPatient.setGender('M');
        actualPatient.setId(5L);
        actualPatient.setName("ahmad");
        actualPatient.setPassword("ahmadPro");
        actualPatient.setPreviousReports("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");
        actualPatient.setType(Type.Patient);
        assertEquals("street 45", actualPatient.getAddress());
        assertEquals(26, actualPatient.getAge());
        assertEquals("ahmad@gmail.com", actualPatient.getEmail());
        assertEquals('M', actualPatient.getGender());
        assertEquals(5L, actualPatient.getId());
        assertEquals("ahmad", actualPatient.getName());
        assertEquals("ahmadPro", actualPatient.getPassword());
        assertEquals("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW", actualPatient.getPreviousReports());
        assertEquals(Type.Patient, actualPatient.getType());
    }
}

