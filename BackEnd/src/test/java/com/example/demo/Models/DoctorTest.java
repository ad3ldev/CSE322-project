package com.example.demo.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoctorTest {
    @Test
    void testDoctorConstructor() {
        Doctor doctor = new Doctor(
                "Ahmad Hassan",
                DoctorSpeciality.Family,
                1, "Alex Sidi Gaber",
                30,
                200,
                300,
                "Ahmadpro77@gmail.com",
                "ahmad77#@14");

        assertEquals("Alex Sidi Gaber", doctor.getAddress());
        assertEquals(1, doctor.getYearsOfExperience());
        assertEquals(Type.Doctor, doctor.getType());
        assertEquals(DoctorSpeciality.Family, doctor.getSpecialization());
        assertEquals("ahmad77#@14", doctor.getPassword());
        assertEquals("Ahmad Hassan", doctor.getName());
        assertEquals(300, doctor.getFollowUpPrice());
        assertEquals("Ahmadpro77@gmail.com", doctor.getEmail());
        assertEquals(200, doctor.getConsultationPrice());
        assertEquals(30, doctor.getAge());
        assertEquals(0L, (new Doctor()).getId());
    }

    @Test
    void testDoctorSettersAndGetters() {
        Doctor doctor = new Doctor();
        doctor.setAddress("Cairo");
        doctor.setAge(45);
        doctor.setConsultationPrice(150);
        doctor.setEmail("youssef@yahoo.com");
        doctor.setFollowUpPrice(120);
        doctor.setName("youssef");
        doctor.setPassword("youssefCSE");
        doctor.setSpecialization(DoctorSpeciality.Oncologist);
        doctor.setYearsOfExperience(3);
        assertEquals("Cairo", doctor.getAddress());
        assertEquals(45, doctor.getAge());
        assertEquals(150, doctor.getConsultationPrice());
        assertEquals("youssef@yahoo.com", doctor.getEmail());
        assertEquals(120, doctor.getFollowUpPrice());
        assertEquals("youssef", doctor.getName());
        assertEquals("youssefCSE", doctor.getPassword());
        assertEquals(DoctorSpeciality.Oncologist, doctor.getSpecialization());
        assertEquals(3, doctor.getYearsOfExperience());
    }
}
