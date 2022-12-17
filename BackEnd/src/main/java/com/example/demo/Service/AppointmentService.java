package com.example.demo.Service;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Models.Appointment;
import com.example.demo.Repo.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public void addAppointment(Appointment appointment) {
        boolean isAppointmentExist = appointmentRepository.isAppointmentExist(appointment.getAppointmentPrimaryData());
        if (isAppointmentExist) {
            throw new BadRequestException("Appointment is taken!");
        }
        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Appointment appointment) {
        boolean isAppointmentExist = appointmentRepository.isAppointmentExist(appointment.getAppointmentPrimaryData());
        if (!isAppointmentExist) {
            throw new BadRequestException("Appointment is not Exist!");
        }
        appointmentRepository.deleteById(appointment.getAppointmentPrimaryData());
    }
}
