import { useState } from "react";
import "./Login.scoped.css";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
	const navigate = useNavigate();
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

	const emailAlert = document.querySelector(".email__alert");
	function submit(e) {
		e.preventDefault();
		if (
			loggedPerson.type === "" ||
			loggedPerson.email === "" ||
			loggedPerson.password === ""
		) {
			showAlert();
			setTimeout(removeAlert, 3000);
			validateEmail(loggedPerson.email);
		} else {
			validateEmail(loggedPerson.email);
			axios.post("/login", loggedPerson).then((response) => {
				const res = response.data;
				console.log(res);
				if (res.id >= 0 && res.state === "SUCCESS") {
					if (loggedPerson.type === "Patient") {
						navigate("/home", { state: res });
					} else if (loggedPerson.type === "Doctor") {
						navigate("/dashboard", { state: res });
					}
				} else {
					showAlert();
				}
			});
		}
	}

	const validateEmail = (address) => {
		const check = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		if (check.test(address) & (address !== null) & (address !== "")) {
			check.test(address);
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
							<i className='fas fa-user-md' />
						</span>
						<h3>Doctor</h3>
						<span className='selected'>
							<i className='fas fa-check' />
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
							<i className='fas fa-bed' />
						</span>
						<h3>Patient</h3>
						<span className='selected'>
							<i className='fas fa-check' />
						</span>
					</label>
				</div>

				<span className='message__selection' />

				<div className='email'>
					<i className='far fa-envelope icon' />
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
					<i className='fas fa-lock icon' />
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
								Sign Up
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
