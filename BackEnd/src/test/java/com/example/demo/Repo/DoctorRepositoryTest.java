package com.example.AnotherDemo.Repo;

import com.example.AnotherDemo.Models.Doctor;
import com.example.AnotherDemo.Models.DoctorSpeciality;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldCheckIfDoctorExistsByEmail() {
        // given
        String email = "ahmad@gmail.com";
        Doctor doctor = new Doctor(
            "Ahmad",
             DoctorSpeciality.Oncologist,
            5,
            "Alex",
            31,
            200,
            500,
            email,
            "ahmad55"
        );
        underTest.save(doctor);
        // when
        boolean expected = underTest.isEmailExist(email);
        // then
        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckIfEmailNotExist() {
        // given
        String email = "salem@gmail.com";
        // when
        boolean expected = underTest.isEmailExist(email);
        // then
        assertThat(expected).isFalse();
    }
}
