package com.example.demo.Models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "appointment_sequence",
            sequenceName = "appointment_sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "appointment_sequence", strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Date date;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Date startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    @Column(nullable = false)
    private Date endTime;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private AppointmentStatus status;
}
