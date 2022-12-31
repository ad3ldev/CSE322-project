import "./SignUpPatient.scoped.css";
import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
const SignUpPatient = () => {
	const navigate = useNavigate();
	const [patient, setPatient] = useState({
		type: "Patient",
		id: "",
		gender: "",
		age: "",
		address: "",
		name: "",
		email: "",
		password: "",
		previousReports: "",
	});
	const handleInfo = (e) => {
		const user = { ...patient };
		if (e.target.id === "age") {
			let x = parseInt(e.target.value, 10);
			user[e.target.id] = x;
		} else {
			user[e.target.id] = e.target.value;
		}
		setPatient(user);
		console.log(patient);
	};
	const createPatient = (e) => {
		e.preventDefault();

		if (
			patient.email === "" ||
			patient.password === "" ||
			patient.name === ""
		) {
			showAlert("Please Enter all of your necessary information");
			return;
		}
		if (validateEmail(patient.email)) {
			axios.post(`/signUp`, patient).then((response) => {
				const res = response.data;
				if (res.id >= 0 && res.state === "SUCCESS") {
					navigate("/home", { state: res });
				} else {
					showAlert("Unable to sign up, Please try again");
				}
			});
		}
	};
	const emailAlert = document.querySelector(".email__alert");

	const validateEmail = (address) => {
		const check = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (check.test(address) && address !== null && address !== "") {
			return true;
		} else {
			showAlert("Please Enter a valid Email");
			return false;
		}
	};

	// email alerts
	function showAlert(message) {
		emailAlert.textContent = message;
		emailAlert.style.display = "block";
		setTimeout(removeAlert, 3000);
	}

	function removeAlert() {
		emailAlert.style.display = "none";
	}
	return (
		<div className='container'>
			<form className='contact__form'>
				<h2>Welcome!</h2>
				<div className='Dname'>
					<i className='far fa-envelope icon' />
					<input
						type='text'
						placeholder='User Name'
						id='name'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Name</span>
					<small className='email__alert'>
						Please enter a valid name
					</small>
				</div>
				<div className='Demail'>
					<i className='far fa-envelope icon' />
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
					<i className='fas fa-lock icon' />
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
				<div className='Demail'>
					<i className='far fa-envelope icon' />
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
					<i className='far fa-envelope icon' />
					<input
						type='text'
						placeholder='Put a link for previous Reports if any.'
						id='previousReports'
						onChange={(e) => {
							handleInfo(e);
						}}
					/>
					<span className='field__name'>Report</span>
				</div>
				<div className='Dage'>
					<i className='far fa-envelope icon' />
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

				<div className='Gender'>
					<select
						name='gender'
						id='gender'
						onChange={(e) => {
							handleInfo(e);
						}}>
						<option value=''>Gender</option>
						<option value='M'>Male</option>
						<option value='F'>Female</option>
					</select>
				</div>

				<div className='Pbottom'>
					<button
						type='submit'
						className='submitBtn'
						onClick={(e) => {
							createPatient(e);
						}}>
						Sign Up
					</button>
				</div>
			</form>
		</div>
	);
};

export default SignUpPatient;
