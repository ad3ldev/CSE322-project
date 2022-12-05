package com.example.demo.Service;

import com.example.demo.Models.Doctor;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import com.example.demo.Repo.DoctorRepository;
import com.example.demo.Repo.PatientRepository;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileCreationService {
    private final PatientRepository pr;
    private final DoctorRepository dr;

    public Result createProfile(String userJsonString) throws JsonProcessingException, JSONException {
        Long id = 0L;
        JSONObject obj = new JSONObject(userJsonString);
        String type = obj.getString("type");
        String email = obj.getString("email");
        System.out.println("type set and its value is" + type);
        Result result = null;

        if (type.equals(Type.Doctor.name())) {
            if (dr.isEmailExist(email)) {
                result = new Result(State.FAILURE, null, null, null, null);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                Doctor doctor = mapper.readValue(userJsonString, Doctor.class);
                dr.save(doctor);
                id = doctor.getId();
                System.out.println("doctor created");
                result = new Result(State.SUCCESS, id, Type.Doctor, null, doctor);
            }
        } else if (type.equals(Type.Patient.name())) {
            if (pr.isEmailExist(email)) {
                result = new Result(State.FAILURE, null, null, null, null);
            } else {
                ObjectMapper mapper = new ObjectMapper();
                Patient patient = mapper.readValue(userJsonString, Patient.class);
                pr.save(patient);
                id = patient.getId();
                System.out.println("patient created");
                result = new Result(State.SUCCESS, id, Type.Patient, patient, null);
            }
        }
        if (result == null) {
            result = new Result(State.FAILURE, null, null, null, null);
            return result;
        } else
            return result;
    }
}
