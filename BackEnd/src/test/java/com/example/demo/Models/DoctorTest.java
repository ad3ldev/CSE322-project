package com.example.demo.Models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DoctorTest {
    @Test
    void testDoctorConstructor() {
        Doctor actualDoctor = new Doctor();
        actualDoctor.setAddress("Cairo");
        actualDoctor.setAge(45);
        actualDoctor.setConsultationPrice(150);
        actualDoctor.setEmail("youssef@yahoo.com");
        actualDoctor.setFollowUpPrice(120);
        actualDoctor.setName("youssef");
        actualDoctor.setPassword("youssefCSE");
        actualDoctor.setSpecialization(DoctorSpeciality.Oncologist);
        actualDoctor.setYearsOfExperience(3);
        assertEquals("Cairo", actualDoctor.getAddress());
        assertEquals(45, actualDoctor.getAge());
        assertEquals(150, actualDoctor.getConsultationPrice());
        assertEquals("youssef@yahoo.com", actualDoctor.getEmail());
        assertEquals(120, actualDoctor.getFollowUpPrice());
        assertEquals("youssef", actualDoctor.getName());
        assertEquals("youssefCSE", actualDoctor.getPassword());
        assertEquals(DoctorSpeciality.Oncologist, actualDoctor.getSpecialization());
        assertEquals(3, actualDoctor.getYearsOfExperience());
    }
}

