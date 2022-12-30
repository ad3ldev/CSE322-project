import { useLocation } from "react-router-dom";

import "./Dashboard.scoped.css";
import { useState, useEffect } from "react";
import axios from "axios";

const DoctorDashboard = () => {
  const { state } = useLocation();
  const doctor = state;

  const [appointments, SetAppointment] = useState([]);
  const [report, setReport] = useState("");

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
    axios.post("/cancelAppointment", appointment).then((response) => {
      const res = response.data;
      //alert(res.description);
    });
    const apps = appointments.filter(
      (app) =>
        app.doctorId !== appointment.doctorId &&
        app.patientId !== appointment.patientId &&
        app.startTime !== appointment.startTime
    );
		console.log(apps);
		SetAppointment(apps);
	};
  const confirmAppointment = (appointment) => {
    appointment.doctorComments = report;
    console.log(appointment);
    axios.post("/makeAppointment", appointment).then((response) => {
      const res = response.data;
      //alert(res.description);
    });
  };

  function showConfirmation(id) {
    var x = document.getElementById(id);
    if (x.style.display === "none") {
      x.style.display = "block";
    } else {
      x.style.display = "none";
    }
  }

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
            <br />
            <h3 style={{ display: "inline" }}>consultation price :</h3>{" "}
            {app.price}$
            <br />
            <br />
            <h3 style={{ display: "inline" }}>appointment date :</h3> {app.date}
            <div class="dash-share">
              <a href="" class="fab fa-facebook-f" />
              <a href="" class="fab fa-twitter" />
              <a href="" class="fab fa-linkedin" />
              <a href="" class="fab fa-instagram" />
            </div>
            <button
              id={app.doctorId + app.patientId + app.startTime}
              onClick={() => {
                showConfirmation("cancelDIV");
                var x = document.getElementById("confirmDIV");
                x.style.display = "none";
              }}
              className="cancel-btn"
            >
              cancel appointment
            </button>
            <button
              id={app.doctorId + app.patientId + app.startTime}
              onClick={() => {
                showConfirmation("confirmDIV");
                var x = document.getElementById("cancelDIV");
                x.style.display = "none";
              }}
              className="confirm-btn"
            >
              confirm appointment
            </button>
            <div style={{ display: "none", marginTop: "10px" }} id="cancelDIV">
              <h4>Are you sure you want to canclel this appointment?</h4>

              <button
                id={app.doctorId + app.patientId + app.startTime}
                onClick={() => cancelAppointment(app)}
                className="cancel-btn"
                style={{ fontSize: "18px" }}
              >
                Yes
              </button>
              <button
                id={app.doctorId + app.patientId + app.startTime}
                onClick={() => {
                  showConfirmation("cancelDIV");
                }}
                className="confirm-btn"
                style={{ fontSize: "18px" }}
              >
                No
              </button>
            </div>
            <div style={{ display: "none", marginTop: "10px" }} id="confirmDIV">
              <h4>please, type your report about the session if any</h4>
              <input
                className="report-in"
                style={{ display: "block" }}
                type="text"
                onChange={(e) => setReport(e.target.value)}
              />
              <button
                id={app.doctorId + app.patientId + app.startTime}
                onClick={() => confirmAppointment(app)}
                className="confirm-btn"
              >
                Submit
              </button>
            </div>
          </div>
        ))}
      </div>
      <div className="next-patient" />
      <div className="upcoming" />
    </div>
  );
};

export default DoctorDashboard;
