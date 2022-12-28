package com.example.demo.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PatientTest {
    @Test
    void testPatientSettersAndGetters() {
        Patient patient = new Patient();
        patient.setAddress("street 45");
        patient.setAge(26);
        patient.setEmail("ahmad@gmail.com");
        patient.setGender("M");
        patient.setId(5L);
        patient.setName("ahmad");
        patient.setPassword("ahmadPro");
        patient.setPreviousReports("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");

        assertEquals("street 45", patient.getAddress());
        assertEquals(26, patient.getAge());
        assertEquals("ahmad@gmail.com", patient.getEmail());
        assertEquals("M", patient.getGender());
        assertEquals(5L, patient.getId());
        assertEquals("ahmad", patient.getName());
        assertEquals("ahmadPro", patient.getPassword());
        assertEquals("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW", patient.getPreviousReports());
        assertEquals(Type.Patient, patient.getType());
    }
    
    @Test
    void testConstructor() {
        Patient patient = new Patient("ahmad", "ahmad@gmail.com",
                "ahmadPro", "M", "street 45", 26,
                "https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");
        assertEquals("street 45", patient.getAddress());
        assertEquals(26, patient.getAge());
        assertEquals("ahmad@gmail.com", patient.getEmail());
        assertEquals("M", patient.getGender());
        assertEquals("ahmad", patient.getName());
        assertEquals("ahmadPro", patient.getPassword());
        assertEquals("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW", patient.getPreviousReports());
        assertEquals(Type.Patient, patient.getType());
    }
}
