package com.example.demo.Repo;

import com.example.demo.Models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
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
            value = "SELECT d.id FROM Doctor d WHERE d.email = ?1 AND d.password = ?2")
    List<Long> findDoctorByEmailAndPassword(String email, String password);

    @Query(
            value = "SELECT * from Doctor d where d.specialization = ?1",
            nativeQuery = true)
    List<Doctor> getDoctorBySpecialization(int specialization);


}
