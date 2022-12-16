package com.example.demo.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Entity
@Table
public class Doctor {
    @Id
    @SequenceGenerator(
            name = "doctor_sequence",
            sequenceName = "doctor_sequence",
            allocationSize = 1)
    @GeneratedValue(generator = "doctor_sequence", strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(nullable = false)
    private final Type type = Type.Doctor;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private DoctorSpeciality specialization;
    @Column(nullable = false)
    private int yearsOfExperience;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private int consultationPrice;
    @Column(nullable = false)
    private int followUpPrice;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoctorSpeciality getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorSpeciality specialization) {
        this.specialization = specialization;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getConsultationPrice() {
        return consultationPrice;
    }

    public Doctor(String name, DoctorSpeciality specialization, int yearsOfExperience, String address,
                  int age, int consultationPrice, int followUpPrice, String email, String password) {
        this.name = name;
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.address = address;
        this.age = age;
        this.consultationPrice = consultationPrice;
        this.followUpPrice = followUpPrice;
        this.email = email;
        this.password = password;
    }

//    public Doctor() {
//        this.name = "";
//        this.specialization = DoctorSpeciality.Surgeon;
//        this.yearsOfExperience = 0;
//        this.address = "";
//        this.age = 25;
//        this.consultationPrice = 200;
//        this.followUpPrice = 100;
//        this.email = "";
//        this.password = "";
//    }

    public void setConsultationPrice(int consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    public int getFollowUpPrice() {
        return followUpPrice;
    }

    public void setFollowUpPrice(int followUpPrice) {
        this.followUpPrice = followUpPrice;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {return id;}

    public Type getType() {return type;}

}
