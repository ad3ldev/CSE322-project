package com.example.demo.Service;

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
            if(!ids.isEmpty())
                return new Result(State.SUCCESS, ids.get(0), Type.Doctor);

        }else if(type.equals(Type.Patient.name())){
            //Check if patient exists
            List<Long> ids = pr.findPatientByEmailAndPassword(email, password);
            if(!ids.isEmpty())
                return new Result(State.SUCCESS, ids.get(0), Type.Patient);
        }
        return new Result(State.FAILURE, null, null);
    }
}
