import { Route, Routes } from "react-router-dom";
import axios from "axios";
import Login from "./components/Login/Login";
import SignUpDoctor from "./components/SignUp/SignUpDoctor";
import SignUpPatient from "./components/SignUp/SignUpPatient";
import Dashboard from "./components/Dashboard/Dashboard";
import PatientAppointment from "./components/Appointment/PatientAppointment";

function App() {
	axios.defaults.baseURL = "http://localhost:8080/";
	return (
		<Routes>
			<Route exact path='/' element={<Login />} />
			<Route path='/SignUpDoctor' element={<SignUpDoctor />} />
			<Route path='/SignUpPatient' element={<SignUpPatient />} />
			<Route exact path='/dashboard' element={<Dashboard />} />
			<Route path='/appointment' element={<PatientAppointment />} />
		</Routes>
	);
}

export default App;
