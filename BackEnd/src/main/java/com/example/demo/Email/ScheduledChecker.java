package com.example.demo.Email;

import com.example.demo.Models.Appointment;
import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Repo.AppointmentRepository;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Utils.DatesComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Configuration
@EnableScheduling
public class ScheduledChecker {

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    EmailSender emailSender;

    @Autowired
    PatientRepository pr;

    @Autowired
    DoctorRepository dr;
    @Autowired
    AppointmentRepository ar;

    //The cron for every hour is "0 0 12-17 * * *"
    @Scheduled(cron="*/5 * * * * *")
    void checkIfPatientHasAnAppointmentNextHour(){
        System.out.println("Check if a patient has an appointment next hour.");
        List<Appointment> appointments = ar.findAll();
        for(var appointment: appointments){
            System.out.println("appointment");
            if(DatesComparator.isToday(appointment.getDate(), Calendar.getInstance().getTime())
            && DatesComparator.within_hour(appointment.getStartTime(), Calendar.getInstance().getTime())){
                System.out.println("entered id" + appointment.getPatientId()  + " " + appointment.getDoctorId() + appointment.getPatientId() + appointment.getStartTime() + appointment.getDate() );
                List<Long> patient_ids = new ArrayList<>();
                List<Long> doctors_ids = new ArrayList<>();
                patient_ids.add(appointment.getPatientId());
                doctors_ids.add(appointment.getDoctorId());
                Patient patient = pr.findAllById(patient_ids).get(0);
                Doctor doctor = dr.findAllById(doctors_ids).get(0);
                System.out.println(patient.getName() + " $$ " + doctor.getName());
                String email = patient.getEmail();
                String name = patient.getName();
                String doctorName = doctor.getName();
                String gender = patient.getGender();
                String time = appointment.getStartTime().toString()+ "PM";
                System.out.println(name + doctorName + time + email);
                emailSender.sendEmail(email, name, doctorName, gender, time);
            }
        }
    }

}
