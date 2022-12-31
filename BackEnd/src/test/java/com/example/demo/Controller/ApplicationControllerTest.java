package com.example.demo.Controller;

import com.example.demo.DemoApplication;
import com.example.demo.Models.Doctor;
import com.example.demo.Models.DoctorSpeciality;
import com.example.demo.Models.Patient;
import com.example.demo.Models.Type;
import com.example.demo.Utils.Result;
import com.example.demo.Utils.State;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


/*@RunWith(SpringRunner.class)
@SpringBootTest()*/
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testSignUpWithDoctor() throws JsonProcessingException {
        Doctor actualDoctor = new Doctor();
        actualDoctor.setAddress("Cairo");
        actualDoctor.setAge(45);
        actualDoctor.setConsultationPrice(150);
        actualDoctor.setEmail("aAyoussefF88aw98981010zy11@yahoo.com");
        actualDoctor.setFollowUpPrice(120);
        actualDoctor.setName("youssef");
        actualDoctor.setPassword("youssefCSE");
        actualDoctor.setSpecialization(DoctorSpeciality.Oncologist);
        actualDoctor.setYearsOfExperience(3);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String doctorJsonString = ow.writeValueAsString(actualDoctor);

        HttpEntity<String> request =
                new HttpEntity<String>(doctorJsonString);

        ResponseEntity<Result> response = restTemplate.postForEntity("http://localhost:" + port + "/signUp", doctorJsonString, Result.class);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assert(response.getBody().getId() != null);
        assertEquals(response.getBody().getState(), State.SUCCESS);
        assertEquals(response.getBody().getType(), Type.Doctor);
    }

    @Test
    public void testSignUpWithPatient() throws JsonProcessingException {
        Patient actualPatient = new Patient();
        actualPatient.setAddress("street 45");
        actualPatient.setAge(26);
        actualPatient.setEmail("aAahmadwq@gmail.com");
        actualPatient.setGender("M");
        actualPatient.setId(5L);
        actualPatient.setName("ahmad");
        actualPatient.setPassword("ahmadPro");
        actualPatient.setPreviousReports("https://drive.google.com/drive/folders/1EbJLrEM8UTnIzswN8rw9sPZ8PzQkivDW");
//        actualPatient.setType(Type.Patient);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String patientJsonString = ow.writeValueAsString(actualPatient);

        ResponseEntity<Result> response = restTemplate.postForEntity("http://localhost:" + port + "/signUp", patientJsonString, Result.class);
        System.out.println(response.getBody().getType());
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assert(response.getBody().getId() != null);
        assertEquals(response.getBody().getState(), State.SUCCESS);
        assertEquals(response.getBody().getType(), Type.Patient);
    }

    @Test
    public void testSignUpWithanExistingUser() throws JsonProcessingException {
        Doctor actualDoctor = new Doctor();
        actualDoctor.setAddress("Cairo");
        actualDoctor.setAge(45);
        actualDoctor.setConsultationPrice(150);
        actualDoctor.setEmail("aAyoussefF88aw98981010zy11@yahoo.com");
        actualDoctor.setFollowUpPrice(120);
        actualDoctor.setName("youssef");
        actualDoctor.setPassword("youssefCSE");
        actualDoctor.setSpecialization(DoctorSpeciality.Oncologist);
        actualDoctor.setYearsOfExperience(3);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String doctorJsonString = ow.writeValueAsString(actualDoctor);

        HttpEntity<String> request =
                new HttpEntity<String>(doctorJsonString);

        ResponseEntity<Result> response = restTemplate.postForEntity("http://localhost:" + port + "/signUp", doctorJsonString, Result.class);
        assertEquals(response.getBody().getState(), State.FAILURE);
        assertNull(response.getBody().getId());
        assertNull(response.getBody().getType());
    }

}