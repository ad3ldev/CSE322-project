package com.example.demo.Utils;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.AppointmentStatus;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class JsonCustomMapper {
    private final DoctorRepository dr;
    private final PatientRepository pr;
    public Appointment jsonToAppointment(String json) throws JsonProcessingException, JSONException {
        JSONObject obj = new JSONObject(json);
        Date date = Date.valueOf(String.valueOf(obj.get("date")));
        Long doctorId = Integer.toUnsignedLong(obj.getInt("doctorId"));
        Long patientId = Integer.toUnsignedLong(obj.getInt("patientId"));
        double price = obj.getDouble("price");
        String period = obj.getString("period");
        String start_end_times[] = period.split("-");
        if(start_end_times[0].equals("12"))
            start_end_times[0] = "0";
        Time startTime = Time.valueOf(String.valueOf(Integer.parseInt(start_end_times[0])+12).concat(":00:00"));
        Time endTime = Time.valueOf(String.valueOf(Integer.parseInt(start_end_times[0])+13).concat(":00:00"));
        List<Long> doctorIds = new ArrayList<>(); doctorIds.add(doctorId);
        List<Long> patientIds = new ArrayList<>();  patientIds.add(patientId);
        return new Appointment(dr.findAllById(doctorIds).get(0),
                pr.findAllById(patientIds).get(0),
                date, startTime, endTime, price, AppointmentStatus.IN_PROGRESS);
    }
    
    public Appointment cancelJsonToAppointment(String json) throws JsonProcessingException {
       // ObjectMapper obj = new ObjectMapper()
        ObjectMapper mapper = new ObjectMapper();
        Appointment app = mapper.readValue(json, Appointment.class);
        return app;
    }
}
