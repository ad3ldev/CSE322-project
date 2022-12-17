package com.example.demo.Repo;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, AppointmentPrimaryData> {
//    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData = ?1")
//    Boolean isAppointmentExist(AppointmentPrimaryData appointmentPrimaryData);

//    @Query(value = "SELECT * FROM Appointment WHERE doctor_id = :id", nativeQuery = true)
//    List<Appointment> getDoctorAppointments(@Param("id") long doctorId);
//
//    @Query(value = "SELECT * FROM Appointment WHERE patient_id = :id", nativeQuery = true)
//    List<Appointment> getPatientAppointments(@Param("id") long patientId);
}
