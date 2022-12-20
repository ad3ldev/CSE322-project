package com.example.demo.Service;

import com.example.demo.Exception.BadRequestException;
import com.example.demo.Models.Appointment;
import com.example.demo.Repo.AppointmentRepository;
import com.example.demo.Utils.Appointment_Result;
import com.example.demo.Utils.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }
    public List<Appointment> getDoctorAppointments(long doctorId) {
        List<Appointment> appointments = getAllAppointments();
        List<Appointment> doctorAppointments = new ArrayList<>();
        for (Appointment appointment: appointments)
            if (appointment.getDoctorId() == doctorId)
                doctorAppointments.add(appointment);
        return doctorAppointments;
    }

    public List<Appointment> getPatientAppointments(long patientId) {
        List<Appointment> appointments = getAllAppointments();
        List<Appointment> patientAppointments = new ArrayList<>();
        for (Appointment appointment: appointments)
            if (appointment.getPatientId() == patientId)
                patientAppointments.add(appointment);
        return patientAppointments;
    }

    public Appointment_Result addAppointment(Appointment appointment) throws JSONException, JsonProcessingException {
        boolean isAppointmentExist = appointmentRepository.existsById(appointment.getAppointmentPrimaryData());
        if (isAppointmentExist) {
            return new Appointment_Result(State.FAILURE, "Appointment is taken!", null);
        }
        appointmentRepository.save(appointment);
        return new Appointment_Result(State.SUCCESS, "Appointment successfully saved.", appointment);
    }

    public Appointment_Result deleteAppointment(Appointment appointment) throws JSONException, JsonProcessingException {
        boolean isAppointmentExist = appointmentRepository.existsById(appointment.getAppointmentPrimaryData());
        if (!isAppointmentExist) {
            return new Appointment_Result(State.FAILURE, "Appointment doesn't Exist!", null);
        }
        appointmentRepository.deleteById(appointment.getAppointmentPrimaryData());
        return new Appointment_Result(State.SUCCESS, "Appointment successfully deleted.", appointment);
    }
}
