package com.example.demo.Service;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@AllArgsConstructor
@Service
public class AuthenticationService {
    private final PatientRepository pr;
    private final DoctorRepository dr;

    public Result Login(String infoString) throws JSONException {
        String email=""; String password=""; String type="";
        JSONObject obj = new JSONObject(infoString);
        email = obj.getString("email");
        password = obj.getString("password");
        type = obj.getString("type");
        if(type.equals(Type.Doctor.name())){
            //Check if doctor exists
            List<Long> ids = dr.findDoctorByEmailAndPassword(email, password);
            Doctor doctor;
            if(!ids.isEmpty()) {
                doctor = dr.findAllById(ids).get(0);
                System.out.println(doctor.getEmail());
                return new Result(State.SUCCESS, ids.get(0), Type.Doctor, null, doctor);
            }

        }else if(type.equals(Type.Patient.name())){
            //Check if patient exists
            List<Long> ids = pr.findPatientByEmailAndPassword(email, password);
            Patient patient;
            if(!ids.isEmpty()) {
                patient = pr.findAllById(ids).get(0);
                System.out.println(patient.getEmail());
                return new Result(State.SUCCESS, ids.get(0), Type.Patient, patient, null);
            }
        }
        return new Result(State.FAILURE, null, null, null, null);
    }
}
