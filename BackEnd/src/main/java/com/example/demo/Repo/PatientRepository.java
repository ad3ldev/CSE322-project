package com.example.demo.Repo;

import com.example.demo.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query(
            "SELECT CASE WHEN COUNT(p) > 0 THEN " +
                    "TRUE ELSE FALSE END " +
                    "FROM Patient p " +
                    "WHERE p.email = ?1"
    )
    Boolean isEmailExist(String email);

    @Query(
            value = "SELECT id FROM Patient p WHERE p.email = ?1 AND p.password = ?2",
            nativeQuery = true)
    List<Long> findPatientByEmailAndPassword(String email, String password);
}
