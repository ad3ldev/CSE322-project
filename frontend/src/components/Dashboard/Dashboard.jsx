import DoctorDashboard from "./DoctorDashboard";
import PatientDashboard from "./PatientDashboard";

const Dashboard = () => {
	let doctor = false;
	return (
		<>
			{doctor && <DoctorDashboard />}
			{!doctor && <PatientDashboard />}
		</>
	);
};

export default Dashboard;
