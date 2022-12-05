import DoctorDashboard from "./DoctorDashboard";
import PatientDashboard from "./PatientDashboard";
import "./Dashboard.css";
const Dashboard = () => {
	let isDoctor = false;
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
		<div className='container'>
			{isDoctor && <DoctorDashboard doctor={doctor} />}
			{!isDoctor && <PatientDashboard patient={patient} />}
		</div>
	);
};

export default Dashboard;
