package com.example.demo.Service;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentPrimaryData;
import com.example.demo.Models.AppointmentStatus;
import com.example.demo.Models.Type;
import com.example.demo.Repo.AppointmentRepository;
import com.example.demo.Utils.Appointment_Result;
import com.example.demo.Utils.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
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

    public Appointment_Result addAppointment(Appointment appointment) {
        boolean isAppointmentExist = appointmentRepository.existsById(appointment.getAppointmentPrimaryData());
        if (isAppointmentExist) {
            return new Appointment_Result(State.FAILURE, "Appointment is taken!", null);
        }
        if (isUserBusy(appointment, Type.Doctor)) {
            return new Appointment_Result(State.FAILURE, "The Doctor already has an appointment at this time!", null);
        }
        if (isUserBusy(appointment, Type.Patient)) {
            return new Appointment_Result(State.FAILURE, "The Patient already has an appointment at this time!", null);
        }
        appointmentRepository.save(appointment);
        return new Appointment_Result(State.SUCCESS, "Appointment successfully saved.", appointment);
    }

    private boolean isUserBusy(Appointment appointment, Type type) {
        Date date = appointment.getDate();
        Time startTime = appointment.getStartTime();
        Time endTime = appointment.getEndTime();
        List<Appointment> appointments;
        if (type == Type.Doctor)
            appointments = getDoctorAppointments(appointment.getDoctorId());
        else
            appointments = getPatientAppointments(appointment.getPatientId());
        for (Appointment app: appointments) {
            if (app.getStartTime().equals(startTime)
                    && app.getEndTime().equals(endTime)
                    && app.getDate().equals(date))
                return true;
        }
        return false;
    }

    public Appointment_Result deleteAppointment(Appointment appointment) {
        boolean isAppointmentExist = appointmentRepository.existsById(appointment.getAppointmentPrimaryData());
        if (!isAppointmentExist) {
            return new Appointment_Result(State.FAILURE, "Appointment doesn't Exist!", null);
        }
        appointmentRepository.deleteById(appointment.getAppointmentPrimaryData());
        return new Appointment_Result(State.SUCCESS, "Appointment successfully deleted.", appointment);
    }

    public void confirmAppointment(AppointmentPrimaryData appointmentPrimaryData, String doctorComments){
        Appointment appointment = null;
        List<Appointment> appointments = appointmentRepository.getAppointmentByPrimaryData(appointmentPrimaryData);
        if(appointments.size() > 0)
            appointment = appointments.get(0);
        else
            return;
        appointmentRepository.confirmAppointment(AppointmentStatus.COMPLETED, doctorComments, appointmentPrimaryData);
    }
}
