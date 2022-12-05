import "./SignUpDoctor.css";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const SignUpDoctor = () => {
	const navigate = useNavigate();

	const [doctor, setDoctor] = useState({
		type: "Doctor",
		id: "",
		address: "",
		name: "",
		email: "",
		password: "",
		specialization: "",
		consultationPrice: 0,
		followUpPrice: 0,
		yearsOfExperience: 0,
	});
	const handleInfo = (e) => {
		const user = { ...doctor };
		if (
			e.target.id === "age" ||
			e.target.id === "consultationPrice" ||
			e.target.id === "followUpPrice" ||
			e.target.id === "yearsOfExperience"
		) {
			let x = parseInt(e.target.value, 10);
			user[e.target.id] = x;
		} else {
			user[e.target.id] = e.target.value;
		}
		setDoctor(user);
		console.log(user);
	};
	const createDoctor = (e) => {
		console.log(doctor);
		e.preventDefault();

		if (
			doctor.email === "" ||
			doctor.password === "" ||
			doctor.name === ""
		) {
			showAlert();
			setTimeout(removeAlert, 3000);
			validateEmail(doctor.email);
		} else {
			validateEmail(doctor.email);
			axios.post("/signUp", doctor).then((response) => {
				const res = response.data;
				console.log(response.data.id);
				if (res.id >= 0 && res.state === "SUCCESS") {
					navigate("/dashboard");
				} else {
					showAlert();
				}
			});
		}
	};
	const emailAlert = document.querySelector(".email__alert");
	const passwordAlert = document.querySelector(".password__alert");

	const validateEmail = (address) => {
		console.log(address);
		const check =
			/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (check.test(address) & (address !== null) & (address !== "")) {
			console.log(check.test(address));
		} else {
			showAlert();
			setTimeout(removeAlert, 3000);
		}
	};

	// email alerts
	function showAlert() {
		emailAlert.style.display = "block";
	}

	function removeAlert() {
		emailAlert.style.display = "none";
	}

	return (
		<div className='Dcontainer'>
			<form className='contact__form'>
				<h2>Welcome, doctor</h2>
				<div className='Dname'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='User Name'
						id='name'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Name</span>
					<small className='name__alert'>
						Please enter a valid name
					</small>
				</div>
				<div className='Demail'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='Email'
						id='email'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Email</span>
					<small className='email__alert'>
						Please enter a valid email address
					</small>
				</div>

				<div className='Demail'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='Address'
						id='address'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Address</span>
					<small className='email__alert'>
						Please enter a valid address
					</small>
				</div>

				<div className='Demail'>
					<i className='fas fa-lock icon'></i>
					<input
						type='password'
						placeholder='Password'
						id='password'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Password</span>
					<small className='password__alert'>
						6 to 20 characters and contain one number and one
						uppercase letter
					</small>
				</div>

				<div className='Dage'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='Age'
						id='age'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Age</span>
					<small className='email__alert'>
						Age can't be empty or negative
					</small>
				</div>

				<div className='Specialization'>
					<select
						name='Specializtion'
						id='specialization'
						onChange={(e) => {
							handleInfo(e);
						}}>
						<option value=''>
							Please choose your specialization
						</option>
						<option value='Family'>Family</option>
						<option value='Internal'>Internal</option>
						<option value='Paediatrician'>Paediatrician</option>
						<option value='Cardiologist'>Cardiologist</option>
						<option value='Oncologist'>Oncologist</option>
						<option value='Gastroenterologist'>
							Gastroenterologist
						</option>
						<option value='Pulmonologist'>Pulmonologist</option>
						<option value='InfectiousDisease'>
							InfectiousDisease
						</option>
						<option value='Endocrinologist'>Endocrinologist</option>
						<option value='Ophthalmologist'>Ophthalmologist</option>
						<option value='Dermatologist'>Dermatologist</option>
						<option value='Psychiatrist'>Psychiatrist</option>
						<option value='Neurologist'>Neurologist</option>
						<option value='Radiologist'>Radiologist</option>
						<option value='Anesthesiologist'>
							Anesthesiologist
						</option>
						<option value='Surgeon'>Surgeon</option>
						<option value='PhysicianExecutive'>
							PhysicianExecutive
						</option>
					</select>
				</div>

				<div className='Years'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='Years of experience'
						id='yearsOfExperience'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Years of experience</span>
					<small className='email__alert'>
						Age can't be empty or negative
					</small>
				</div>
				<div className='Cprise'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='consultation Price'
						id='consultationPrice'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>consultation Price</span>
					<small className='email__alert'>
						Age can't be empty or negative
					</small>
				</div>
				<div className='Fprise'>
					<i className='far fa-envelope icon'></i>
					<input
						type='text'
						placeholder='followUp Price'
						id='followUpPrice'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>followUp Price</span>
					<small className='email__alert'>
						Age can't be empty or negative
					</small>
				</div>

				<div className='Dbottom'>
					<button
						type='submit'
						className='submitBtn'
						onClick={(e) => {
							createDoctor(e);
						}}>
						Sign Up
					</button>
				</div>
			</form>
		</div>
	);
};

export default SignUpDoctor;
