import "./SignUpPatient.css";
import { useState } from "react";
import axios from "axios";
const SignUpPatient = () => {
	const [pateint, setpateint] = useState({
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
		const user = { ...pateint };
		if (e.target.id === "age") {
			let x = parseInt(e.target.value, 10);
			user[e.target.id] = x;
		} else {
			user[e.target.id] = e.target.value;
		}
		setpateint(user);
	};
	const createPatient = (e) => {
		console.log(pateint);
		e.preventDefault();

		if (
			pateint.email === "" ||
			pateint.password === "" ||
			pateint.name === ""
		) {
			showAlert();
			setTimeout(removeAlert, 3000);
			validateEmail(pateint.email);
		} else {
			validateEmail(pateint.email);
			axios.post(`/signUp`, pateint);
		}
	};
	const emailAlert = document.querySelector(".email__alert");
	const passwordAlert = document.querySelector(".password__alert");

	const validateEmail = (address) => {
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
		<div className='container'>
			<form className='contact__form'>
				<h2>Welcome!</h2>
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
					<small className='email__alert'>
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
