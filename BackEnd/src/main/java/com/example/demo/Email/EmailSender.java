package com.example.demo.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String email, String patient_name, String doctor_name, String gender, String time) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);
        String title = "";
        if(gender.equals("M"))
            title = "Mr.";
        else
            title = "Ms.";
        msg.setSubject("Reminder");
        msg.setText("Hello "+title+patient_name+".\nWe want to remind you " +
                "that you have an appointment at "+time+". with Dr."+doctor_name+".\nStay Safe.");

        javaMailSender.send(msg);
    }

}
