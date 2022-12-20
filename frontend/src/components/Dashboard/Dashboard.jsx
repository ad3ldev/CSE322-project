import DoctorDashboard from "./DoctorDashboard";
import PatientDashboard from "./PatientDashboard";
import "./Dashboard.scoped.css";
import { useLocation } from "react-router-dom";
const Dashboard = () => {
	const { state } = useLocation();
	let isDoctor = state.type === "Doctor" ? true : false;
	const data = state;
	return (
		<div className='dashboard'>
			<div className='top-bar'>
				<p>Search</p>
				<input type='text' />
			</div>
			<div className='info'>
				{isDoctor && <DoctorDashboard doctor={data} />}
				{!isDoctor && <PatientDashboard patient={data} />}
			</div>
		</div>
	);
};

export default Dashboard;
