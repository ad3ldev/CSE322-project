import { useLocation } from "react-router-dom";

import "./Dashboard.scoped.css";
import { useState, useEffect } from "react";
import axios from "axios";

const DoctorDashboard = () => {
  const { state } = useLocation();
  const doctor = state;
  console.log(doctor);
  const [appointments, SetAppointment] = useState([]);
  useEffect(() => {
    console.log(doctor.id);
    const getAppointments = async () => {
      await axios
        .post("/getDoctorAppointments", doctor.id.toString())
        .then((response) => {
          const res = response.data;
          console.log(res);
          SetAppointment(res);
        });
    };
    getAppointments();
  }, [doctor]);
  const cancelAppointment = (appointment) => {
    axios.post("/cancelAppointment", appointment).then((response) => {});
    const apps = appointments.filter(
      (app) =>
        app.doctorId !== appointment.doctorId &&
        app.patientId !== appointment.patientId &&
        app.startTime !== appointment.startTime
    );

    console.log(apps);
    SetAppointment(apps);
  };

  return (
    <div className="dash">
      <div className="dash-name">
        <h1>
          Hello <span>Dr.{state.doctor.name}</span>{" "}
        </h1>
      </div>
      <div className="dash-app">
        <h1>
          your <span>appointments</span>{" "}
        </h1>
      </div>
      <div class="dash-container">
        {appointments.map((app) => (
          <div class="dash-box" key={app.id} id={app.id}>
            <h3>patient name</h3>
            <span>consultation price : {app.price}$</span>
            <div class="dash-share">
              <a href="" class="fab fa-facebook-f" />
              <a href="" class="fab fa-twitter" />
              <a href="" class="fab fa-linkedin" />
              <a href="" class="fab fa-instagram" />
            </div>
            <button
              id={app.doctorId + app.patientId + app.startTime}
              onClick={() => cancelAppointment(app)}
              className="cancel-btn"
            >
              cancel appointment
            </button>
          </div>
        ))}
      </div>
      <div className="next-patient" />
      <div className="upcoming" />
    </div>
  );
};

export default DoctorDashboard;
