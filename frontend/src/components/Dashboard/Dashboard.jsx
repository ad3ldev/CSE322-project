import DoctorDashboard from "./DoctorDashboard";
import PatientDashboard from "./PatientDashboard";
import "./Dashboard.css";
const Dashboard = () => {
	let isDoctor = true;
	const patient = {
		type: "Patient",
		id: "1",
		gender: "F",
		age: "30",
		address: "Patient Address",
		name: "Patient Zero",
		email: "Patient0@mail.com",
		previousReports: "",
	};
	const doctor = {
		type: "Doctor",
		id: "1",
		address: "Doctor Address",
		name: "Doctor X",
		email: "DoctorX@email.com",
		specialization: "Internal",
		consultationPrice: 200,
		followUpPrice: 100,
		yearsOfExperience: 5,
	};
	return (
		<div className='dashboard'>
			<div className='top-bar'>
				<p>Search</p>
				<input type='text' />
			</div>
			<div className='info'>
				{isDoctor && <DoctorDashboard doctor={doctor} />}
				{!isDoctor && <PatientDashboard patient={patient} />}
			</div>
		</div>
	);
};

export default Dashboard;
