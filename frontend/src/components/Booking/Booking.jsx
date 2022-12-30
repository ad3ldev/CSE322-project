import { useState } from "react";
import { useLocation } from "react-router-dom";
import "./Booking.scoped.css";
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
		setBtn(false);
		console.log(Appointment);
		axios.post("/makeAppointment", Appointment).then((response) => {
			const res = response.data;
			alert(res.description);
		});
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
							<span className='pic'>
								<i className='fas fa-user-md' />
							</span>
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
					<h1 className='form-h1'>pick date:</h1>
					<input
						type='date'
						className='date-form'
						onChange={(e) => {
							const appointment = { ...Appointment };
							appointment.date = e.target.value;
							SetAppointment(appointment);
						}}
					/>
					<br />
					<br />
					<h1 className='form-h1'>pick hour:</h1>
					<select
						name='Specializtion'
						id='specialization'
						className='date-form'
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
					<h1 className='form-h1'>Accepted Cards</h1>

					<div class='icon-container'>
						<i
							class='fab fa-cc-visa'
							style={{ color: "navy", marginRight: "5px" }}
						/>
						<i
							class='fab fa-cc-mastercard'
							style={{ color: "red", marginRight: "5px" }}
						/>
						<i
							class='fab fa-cc-amazon-pay'
							style={{ marginRight: "5px" }}
						/>
						<i
							class='fab fa-cc-amex'
							style={{ color: "blue", marginRight: "5px" }}
						/>

						<i
							class='fa fa-cc-discover'
							style={{ color: "orange" }}
						/>
					</div>

					<input
						type='number'
						placeholder='your card number'
						class='box-form'
					/>
					<br />
					<br />
					<button
						className='submit-app'
						onClick={(e) => makeAppointment(e)}>
						Confirm Appointment
					</button>
				</PopUp>
			</section>
		</div>
	);
};

export default Booking;
