package com.example.demo.Repo;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentPrimaryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, AppointmentPrimaryData> {
    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData = ?1")
    Boolean isAppointmentExist(AppointmentPrimaryData appointmentPrimaryData);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData.doctorId = ?1")
    List<Appointment> getDoctorAppointments(long doctorId);

    @Query(value = "SELECT a FROM Appointment a WHERE a.appointmentPrimaryData.patientId = ?1")
    List<Appointment> getPatientAppointments(long patientId);
}
