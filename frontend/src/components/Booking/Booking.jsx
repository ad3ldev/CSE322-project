import { useState } from "react";
import { useLocation } from "react-router-dom";
import "./Booking.scoped.css";
import doc1 from "./doc-1.jpg";
import axios from "axios";

import PopUp from "./PopUp";
const Booking = () => {
	const { state } = useLocation();
	const doctors = state;
	const [Appointment, SetAppointment] = useState({
		doctorId: -1,
		patientId: doctors.patient.id,
		date: "",
		period: "",
		price: "",
	});
	const [popupBtn, setBtn] = useState(false);
	const Form = (doctor) => {
		setBtn(true);
		const appointment = { ...Appointment };
		appointment.doctorId = parseInt(doctor.id);
		appointment.price = parseInt(doctor.consultationPrice);
		SetAppointment(appointment);
		console.log(Appointment);
	};
	const makeAppointment = (e) => {
		e.preventDefault();
		console.log(Appointment);
		axios.post("/makeAppointment", Appointment);
	};

	return (
		<div
			style={{
				fontSize: "62.5%",
				overflowX: "hidden",
				scrollPaddingTop: "7rem",
				scrollBehavior: "smooth",
				background: "none",
				backgroundColor: "white",
			}}>
			<section class='doctors' id='doctors'>
				<h1 class='heading'>
					Best <span>doctors</span> for{" "}
					{doctors.doctors[0].specialization}
				</h1>
				<div class='box-container'>
					{doctors.doctors.map((doc) => (
						<div
							class='box'
							key={doc.id}
							id={doc.id}
							onClick={() => Form(doc)}>
							<img src={doc1} alt='' />
							<h3>{doc.name}</h3>
							<span>
								consultation price : {doc.consultationPrice}$
							</span>
							<div class='share'>
								<a href='' class='fab fa-facebook-f' />
								<a href='' class='fab fa-twitter' />
								<a href='' class='fab fa-linkedin' />
								<a href='' class='fab fa-instagram' />
							</div>
						</div>
					))}
				</div>

				<PopUp trigger={popupBtn} setTrigger={setBtn}>
					<h1>pick date</h1>
					<input
						type='date'
						onChange={(e) => {
							const appointment = { ...Appointment };
							appointment.date = e.target.value;
							SetAppointment(appointment);
						}}
					/>
					<br />
					<br />
					<select
						name='Specializtion'
						id='specialization'
						className='box'
						onChange={(e) => {
							const appointment = { ...Appointment };
							appointment.period = e.target.value;
							SetAppointment(appointment);
							console.log(Appointment);
						}}>
						<option value=''>Hour</option>
						<option value='12-1'>12-1</option>
						<option value='1-2'>1-2</option>
						<option value='2-3'>2-3</option>
						<option value='3-4'>3-4</option>
						<option value='4-5'>4-5</option>
					</select>

					<br />
					<br />
					<button onClick={(e) => makeAppointment(e)}>
						Make Appointment
					</button>
				</PopUp>
			</section>
		</div>
	);
};

export default Booking;
