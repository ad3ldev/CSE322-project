import "./Home.scoped.css";
import { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import aboutImg from "./image/about-img.svg";
import blog1 from "./image/blog-1.jpg";
import blog2 from "./image/blog-2.jpg";
import blog3 from "./image/blog-3.jpg";
import bookImg from "./image/book-img.svg";
import doc1 from "./image/doc-1.jpg";
import doc2 from "./image/doc-2.jpg";
import doc3 from "./image/doc-3.jpg";
import doc4 from "./image/doc-4.jpg";
import doc5 from "./image/doc-5.jpg";
import doc6 from "./image/doc-6.jpg";
import homeImg from "./image/home-img.svg";
import pic1 from "./image/pic-1.png";
import pic2 from "./image/pic-2.png";
import pic3 from "./image/pic-3.png";

const Home = () => {
	const navigate = useNavigate();
	const { state } = useLocation();
	const patient = state;
	const [appointments, SetAppointment] = useState([]);
	console.log(patient);

	useEffect(() => {
		console.log(patient.id);
		const getAppointments = async () => {
			await axios
				.post("/getPatientAppointments", patient.id.toString())
				.then((response) => {
					const res = response.data;
					SetAppointment(res);
				});
		};
		getAppointments();
	}, [patient]);
	console.log(appointments);
	const [bookingInfo, setInfo] = useState({
		name: "",
		email: "",
		number: "",
		specialization: "",
	});
	const handleInfo = (e) => {
		const user = { ...bookingInfo };
		user[e.target.id] = e.target.value;
		setInfo(user);
		console.log(bookingInfo);
	};

	const sendInfo = (e) => {
		axios
			.post("/searchBySpecialization", bookingInfo.specialization)
			.then((response) => {
				const res = { doctors: response.data, patient: patient };
				console.log(res);
				navigate("/booking", { state: res });
			});
		e.preventDefault();
	};
	const cancelAppointment = (appointment) => {
		axios.post("/cancelAppointment", appointment).then((response) => {});
		SetAppointment(
			appointments.filter(
				(app) =>
					app.doctorId !== appointment.doctorId &&
					app.patientId !== appointment.patientId,
			),
		);
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
		<div
			className='ggg'
			style={{
				fontSize: "62.5%",
				overflowX: "hidden",
				scrollPaddingTop: "7rem",
				scrollBehavior: "smooth",
				background: "none",
			}}>
			<div className='header'>
				<a href='#' className='logo'>
					<i className='fas fa-heartbeat' /> medcare
				</a>
				<nav className='navbar'>

					<a href='#home'>home</a>
					<a href='#services'>services</a>
					<a href='#about'>about</a>
					<a href='#doctors'>doctors</a>
					<a href='#book'>book</a>
					<a href='#appointments'>appointments</a>
					<a href='#review'>reviews</a>
					<a href='#blogs'>blogs</a>
					<a href='/login'>logout</a>
				</nav>
				<div id='menu-btn' className='fas fa-bars' />

			</div>

			<section className='home' id='home'>
				<div className='image'>
					<img src={homeImg} alt='home-img.svg' />
				</div>
				<div className='content'>
					<h3>stay safe, stay healthy</h3>
					<p>
						Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing Elit.
						Rem Sed Autem Vero? Magnam, Est Laboriosam!
					</p>
					<a href='#' className='btn'>
						contact us <span className='fas fa-chevron-right' />{" "}
					</a>
				</div>
			</section>

			<section className='icons-container'>
				<div className='icons'>
					<i className='fas fa-user-md' />
					<h3>140+</h3>
					<p>doctors at work</p>
				</div>
				<div className='icons'>
					<i className='fas fa-users' />
					<h3>1040+</h3>
					<p>satisfied patients</p>
				</div>
				<div className='icons'>
					<i className='fas fa-procedures' />
					<h3>500+</h3>
					<p>bed facility</p>
				</div>
				<div className='icons'>
					<i className='fas fa-hospital' />
					<h3>80+</h3>
					<p>available hospitals</p>
				</div>
			</section>

			<section className='services' id='services'>
				<h1 className='heading'>
					our <span>services</span>
				</h1>
				<div className='box-container'>
					<div className='box'>
						<i className='fas fa-notes-medical' />
						<h3>free checkups</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
					<div className='box'>
						<i className='fas fa-ambulance' />
						<h3>24/7 ambulance</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
					<div className='box'>
						<i className='fas fa-user-md' />
						<h3>expert doctors</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
					<div className='box'>
						<i className='fas fa-pills' />
						<h3>medicines</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
					<div className='box'>
						<i className='fas fa-procedures' />
						<h3>bed facility</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
					<div className='box'>
						<i className='fas fa-heartbeat' />
						<h3>total care</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Ad, Omnis.
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
				</div>
			</section>

			<section className='about' id='about'>
				<h1 className='heading'>
					<span>about</span> us
				</h1>
				<div className='row'>
					<div className='image'>
						<img src={aboutImg} alt='' />
					</div>
					<div className='content'>
						<h3>we take care of your healthy life</h3>
						<p>
							Lorem Ipsum Dolor Sit Amet Consectetur, Adipisicing
							Elit. Iure Ducimus, Quod Ex Cupiditate Ullam In
							Assumenda Maiores Et Culpa Odit Tempora Ipsam Qui,
							Quisquam Quis Facere Iste Fuga, Minus Nesciunt.
						</p>
						<p>
							Lorem Ipsum Dolor, Sit Amet Consectetur Adipisicing
							Elit. Natus Vero Ipsam Laborum Porro Voluptates
							Voluptatibus A Nihil Temporibus Deserunt Vel?
						</p>
						<a href='#' className='btn'>
							learn more <span className='fas fa-chevron-right' />{" "}
						</a>
					</div>
				</div>
			</section>

			<section className='doctors' id='doctors'>
				<h1 className='heading'>
					our <span>doctors</span>
				</h1>
				<div className='box-container'>
					<div className='box'>
						<img src={doc1} alt='' />
						<h3>Dr. john deo</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
					<div className='box'>
						<img src={doc2} alt='' />
						<h3>Dr. Pullen</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
					<div className='box'>
						<img src={doc3} alt='' />
						<h3> Dr. Swallow</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
					<div className='box'>
						<img src={doc4} alt='' />
						<h3>Dr. Mangle</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
					<div className='box'>
						<img src={doc5} alt='' />
						<h3>Dr. Fillmore</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
					<div className='box'>
						<img src={doc6} alt='' />
						<h3>Dr. Watamaniuk</h3>
						<span>expert doctor</span>
						<div className='share'>
							<a href='#' className='fab fa-facebook-f' />
							<a href='#' className='fab fa-twitter' />
							<a href='#' className='fab fa-linkedin' />
							<a href='#' className='fab fa-instagram' />
						</div>
					</div>
				</div>
			</section>

			<section className='book' id='book'>
				<h1 className='heading'>
					<span>book</span> now
				</h1>
				<div className='row'>
					<div className='image'>

						<img src={bookImg} alt='' />
					</div>
					<form action=''>
						<h3>book appointment</h3>
						<input
							type='text'
							placeholder='your name'
							className='box'

							id='name'
							onChange={(e) => {
								handleInfo(e);
							}}
						/>
						<input
							type='number'
							placeholder='your number'
							className='box'

							id='number'
							onChange={(e) => {
								handleInfo(e);
							}}
						/>
						<input
							type='email'
							placeholder='your email'
							className='box'

							id='email'
							onChange={(e) => {
								handleInfo(e);
							}}
						/>
						<select
							name='Specializtion'
							id='specialization'
							className='box'
							onChange={(e) => {
								handleInfo(e);
							}}>
							<option value=''>Specialization</option>
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
							<option value='Endocrinologist'>
								Endocrinologist
							</option>
							<option value='Ophthalmologist'>
								Ophthalmologist
							</option>
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
						<input
							type='submit'
							value='book now'
							className='btn'

							onClick={(e) => {
								sendInfo(e);
							}}
						/>
					</form>
				</div>
			</section>
			<section className='review' id='appointments'>

				<h1 className='heading'>
					Your <span>upcoming</span> appointments
				</h1>
				<div className='box-container'>

					{appointments.length === 0 ? (
						<div className='box'>
							<h3>No upcoming appointments</h3>{" "}
						</div>
					) : (
						appointments.map((app) => (
							<div
								key={
									app.patientId +
									app.doctorId +
									app.date +
									app.startTime
								}
								className='box'>

								<img src={pic1} alt='' />
								<h3>doc name</h3>
								<p
									style={{
										color: "black",
									}}

									className='text'>

								
									Date: {app.date} at {app.startTime} pm.
								</p>
								<button
									id={app.doctorId + app.patientId}
									onClick={() =>
										showConfirmation("cancelDIV")
									}
									className='cancel-btn'>
									cancel appointment
								</button>
								<div
									style={{
										display: "none",
										marginTop: "10px",
									}}
									id='cancelDIV'>
									<h4 style={{ fontSize: "18px" }}>
										Are you sure you want to canclel this
										appointment?
									</h4>

									<button
										id={
											app.doctorId +
											app.patientId +
											app.startTime
										}
										onClick={() => cancelAppointment(app)}
										className='cancel-btn'
										style={{
											fontSize: "18px",
											marginRight: "100px",
										}}>
										Yes
									</button>
									<button
										id={
											app.doctorId +
											app.patientId +
											app.startTime
										}
										onClick={() => {
											showConfirmation("cancelDIV");
										}}
										className='cancel-btn1'
										style={{ fontSize: "18px" }}>
										No
									</button>
								</div>
							</div>
						))
					)}
				</div>
			</section>

			<section className='review' id='review'>
				<h1 className='heading'>
					client's <span>review</span>
				</h1>
				<div className='box-container'>
					<div className='box'>
						<img src={pic1} alt='' />
						<h3>Jazmin Archer</h3>
						<div className='stars'>
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star-half-alt' />
						</div>
						<p className='text'>
							Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing
							Elit. Laboriosam Sapiente Nihil Aperiam? Repellat
							Sequi Nisi Aliquid Perspiciatis Libero Nobis Rem
							Numquam Nesciunt Alias Sapiente Minus Voluptatem,
							Reiciendis Consequuntur Optio Dolorem!
						</p>
					</div>
					<div className='box'>
						<img src={pic2} alt='' />
						<h3>Jazmin Archer</h3>
						<div className='stars'>
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star-half-alt' />
						</div>
						<p className='text'>
							Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing
							Elit. Laboriosam Sapiente Nihil Aperiam? Repellat
							Sequi Nisi Aliquid Perspiciatis Libero Nobis Rem
							Numquam Nesciunt Alias Sapiente Minus Voluptatem,
							Reiciendis Consequuntur Optio Dolorem!
						</p>
					</div>
					<div className='box'>
						<img src={pic3} alt='' />
						<h3>Abdiel Galloway</h3>
						<div className='stars'>
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star' />
							<i className='fas fa-star-half-alt' />
						</div>
						<p className='text'>
							Lorem Ipsum Dolor Sit Amet Consectetur Adipisicing
							Elit. Laboriosam Sapiente Nihil Aperiam? Repellat
							Sequi Nisi Aliquid Perspiciatis Libero Nobis Rem
							Numquam Nesciunt Alias Sapiente Minus Voluptatem,
							Reiciendis Consequuntur Optio Dolorem!
						</p>
					</div>
				</div>
			</section>

			<section className='blogs' id='blogs'>
				<h1 className='heading'>
					our <span>blogs</span>
				</h1>
				<div className='box-container'>
					<div className='box'>
						<div className='image'>
							<img src={blog1} alt='' />
						</div>
						<div className='content'>
							<div className='icon'>
								<a href='#'>
									<i className='fas fa-calendar' />
									1st may, 2021
								</a>
								<a href='#'>
									<i className='fas fa-user' /> by admin
								</a>
							</div>
							<h3>blog title goes here</h3>
							<p>
								Lorem Ipsum, Dolor Sit Amet Consectetur
								Adipisicing Elit. Provident, Eius
							</p>
							<a href='#' className='btn'>
								learn more{" "}
								<span className='fas fa-chevron-right' />{" "}
							</a>
						</div>
					</div>
					<div className='box'>
						<div className='image'>
							<img src={blog2} alt='' />
						</div>
						<div className='content'>
							<div className='icon'>
								<a href='#'>
									<i className='fas fa-calendar' /> 1st may,
									2021
								</a>
								<a href='#'>
									<i className='fas fa-user' /> by admin
								</a>
							</div>
							<h3>blog title goes here</h3>
							<p>
								Lorem Ipsum, Dolor Sit Amet Consectetur
								Adipisicing Elit. Provident, Eius
							</p>
							<a href='#' className='btn'>
								learn more{" "}
								<span className='fas fa-chevron-right' />{" "}
							</a>
						</div>
					</div>
					<div className='box'>
						<div className='image'>
							<img src={blog3} alt='' />
						</div>
						<div className='content'>
							<div className='icon'>
								<a href='#'>
									<i className='fas fa-calendar' /> 1st may,
									2021
								</a>
								<a href='#'>
									<i className='fas fa-user' /> by admin
								</a>
							</div>
							<h3>blog title goes here</h3>
							<p>
								Lorem Ipsum, Dolor Sit Amet Consectetur
								Adipisicing Elit. Provident, Eius
							</p>
							<a href='#' className='btn'>
								learn more{" "}
								<span className='fas fa-chevron-right' />{" "}
							</a>
						</div>
					</div>
				</div>
			</section>
			<section className='footer'>
				<div className='box-container'>
					<div className='box'>
						<h3>quick links</h3>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> home
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> services
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> about
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> doctors
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> book
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> review
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> blogs
						</a>
					</div>
					<div className='box'>
						<h3>our services</h3>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> dental care
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> message
							therapy
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> cardioloty
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> diagnosis
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-chevron-right' /> ambulance
							service
						</a>
					</div>
					<div className='box'>
						<h3>contact info</h3>
						<a href='#'>
							{" "}
							<i className='fas fa-phone' /> +123456789
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-envelope' /> medcare.info.com
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-envelope' />{" "}
							medcare.info.@gmail.com
						</a>
						<a href='#'>
							{" "}
							<i className='fas fa-map-marker-alt' /> Egypt,
							Alexandria
						</a>
					</div>
					<div className='box'>
						<h3>follow us</h3>

						<a href='#'>
							{" "}
							<i className='fab fa-facebook-f' /> facebook
						</a>
						<a href='#'>
							{" "}
							<i className='fab fa-twitter' /> twitter
						</a>
						<a href='#'>
							{" "}
							<i className='fab fa-linkedin' /> linkedin
						</a>
						<a href='#'>
							{" "}
							<i className='fab fa-instagram' /> instagram
						</a>
						<a href='#'>
							{" "}
							<i className='fab fa-youtube' /> youtube
						</a>
						<a href='#'>
							{" "}
							<i className='fab fa-pinterest' /> pinterest
						</a>
					</div>
				</div>
			</section>
		</div>
	);
};

export default Home;
