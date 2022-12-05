import { useState } from "react";
import "./Login.css";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
	const navigate = useNavigate();
	const [confirmed, setConfirm] = useState(0);

	const [loggedPerson, setLoggedPerson] = useState({
		type: "",
		email: "",
		password: "",
	});

	const handleInfo = (e) => {
		const user = { ...loggedPerson };
		if (e.target.value === "Doctor" || e.target.value === "Patient") {
			user.type = e.target.value;
		} else {
			user[e.target.id] = e.target.value;
		}

		setLoggedPerson(user);
	};

	// EMAIL VALIDATION
	/* const form = document.querySelector(".contact__form");
  const email = document.querySelector("#email");
  const password = document.querySelector("#password");
  const submitBtn = document.querySelector(".submitBtn");*/
	const emailAlert = document.querySelector(".email__alert");
	const passwordAlert = document.querySelector(".password__alert");

	function submit(e) {
		console.log(loggedPerson);
		if (loggedPerson.email === null || loggedPerson.password === null) {
			showAlert();
			setTimeout(removeAlert, 3000);
			validateEmail(loggedPerson.email);
		} else {
			validateEmail(loggedPerson.email);
			axios.post("/login", loggedPerson);
		}
		e.preventDefault();
		if (confirmed === 1) {
			e.preventDefault();
			navigate("/dashboard");
		}
	}

	const validateEmail = (address) => {
		console.log(address);
		const check =
			/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if (check.test(address) & (address !== null) & (address !== "")) {
			console.log(check.test(address));
			setConfirm(1);
		} else {
			showAlert();
			setTimeout(removeAlert, 3000);
			setConfirm(0);
		}
	};

	// email alerts
	function showAlert() {
		emailAlert.style.display = "block";
	}

	function removeAlert() {
		emailAlert.style.display = "none";
	}

	/*  // password alerts
  function showPasswordAlert() {
    passwordAlert.style.display = "block";
  }
  function removePasswordAlert() {
    passwordAlert.style.display = "none";
  } */
	return (
		<div className='container'>
			<form className='contact__form'>
				<h2>Choose Account Type</h2>

				<div className='options'>
					<input
						type='radio'
						name='type'
						id='doctor'
						value='Doctor'
						onClick={(e) => {
							handleInfo(e);
						}}
					/>
					<label htmlFor='doctor'>
						<span className='icon__select'>
							<i className='fas fa-user-md'></i>
						</span>
						<h3>Doctor</h3>
						<span className='selected'>
							<i className='fas fa-check'></i>
						</span>
					</label>
					<input
						type='radio'
						name='type'
						id='patient'
						value='Patient'
						onClick={(e) => {
							handleInfo(e);
						}}
					/>
					<label htmlFor='patient'>
						<span className='icon__select'>
							<i className='fas fa-bed'></i>
						</span>
						<h3>Patient</h3>
						<span className='selected'>
							<i className='fas fa-check'></i>
						</span>
					</label>
				</div>

				<span className='message__selection'></span>

				<div className='email'>
					<i className='far fa-envelope icon'></i>
					<input
						onChange={(e) => {
							handleInfo(e);
						}}
						type='text'
						placeholder='Email'
						id='email'
					/>
					<span className='field__name'>Email</span>
					<small className='email__alert'>
						Please enter a valid email address
					</small>
				</div>

				<div className='password'>
					<i className='fas fa-lock icon'></i>
					<input
						onChange={(e) => {
							handleInfo(e);
						}}
						type='password'
						placeholder='Password'
						id='password'
					/>
					<span className='field__name'>Password</span>
					<a href='#/' className='forgot'>
						Forgot?
					</a>
					<small className='password__alert'>
						6 to 20 characters and contain one number and one
						uppercase letter
					</small>
				</div>

				<div className='bottom'>
					<div className='no__acc'>
						<span>No account?</span>
						{loggedPerson.type !== "" && (
							<Link to={`/SignUp${loggedPerson.type}`}>
								{" "}
								Signup
							</Link>
						)}
					</div>

					<button
						type='submit'
						className='submitBtn'
						onClick={(e) => {
							submit(e);
						}}>
						Login
					</button>
				</div>
			</form>
		</div>
	);
};

export default Login;
