package com.example.AnotherDemo.Repo;

import com.example.AnotherDemo.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query(
            "SELECT CASE WHEN COUNT(d) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Doctor d " +
            "WHERE d.email = ?1"
    )
    Boolean isEmailExist(String email);
    @Query(
            value = "SELECT id FROM Doctor d WHERE d.email = ?1 AND d.password = ?2",
            nativeQuery = true
    )
    List<Long> findDoctorByEmailAndPassword(String email, String password);

}
