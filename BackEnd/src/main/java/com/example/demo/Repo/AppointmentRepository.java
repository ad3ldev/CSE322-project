package com.example.demo.Repo;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentPrimaryData;
import com.example.demo.Models.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, AppointmentPrimaryData> {
    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData = ?1")
    Boolean isAppointmentExist(AppointmentPrimaryData appointmentPrimaryData);

    @Query(value = "SELECT * FROM Appointment WHERE doctor_id = :id", nativeQuery = true)
    List<Appointment> getDoctorAppointments(@Param("id") long doctorId);

    @Query(value = "SELECT * FROM Appointment WHERE patient_id = :id", nativeQuery = true)
    List<Appointment> getPatientAppointments(@Param("id") long patientId);

    @Modifying
    @Query(value = "UPDATE Appointment SET status = ?1, doctorComments = ?2 where appointmentPrimaryData = ?3")
    void confirmAppointment(AppointmentStatus status, String doctorComments, AppointmentPrimaryData appointmentPrimaryData);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData = ?1")
    List<Appointment> getAppointmentByPrimaryData(AppointmentPrimaryData appointmentPrimaryData);

}
