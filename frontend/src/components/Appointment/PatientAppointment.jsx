import "./PatientAppointment.css";
const PatientAppointment = () => {
	let appointment = {
		price: 0,
		status: "in progress",
		primaryData: {
			doctorId: 0,
			patientId: 0,
			Date: Date().now,
			startTime: Date().now,
			endTime: Date().now,
		},
	};
	return (
		<>
			<h1>Appointment ID</h1>
			<p>{appointment.status}</p>
			<p>{appointment.primaryData.patientId}</p>
			<p>{appointment.primaryData.Date}</p>
			<p>{appointment.primaryData.startTime}</p>
			<p>{appointment.primaryData.endTime}</p>
			<h2>{appointment.price}</h2>
		</>
	);
};

export default PatientAppointment;
