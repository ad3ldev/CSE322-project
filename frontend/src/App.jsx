import { BrowserRouter, Route, Routes } from "react-router-dom";
import axios from "axios";
import Login from "./components/Login/Login";
import SignUpDoctor from "./components/SignUp/SignUpDoctor";
import SignUpPatient from "./components/SignUp/SignUpPatient";
import Dashboard from "./components/Dashboard/Dashboard";
import Home from "./components/HomePage/Home";
// import Booking from "./components/Booking/Booking";
function App() {
	axios.defaults.baseURL = "http://localhost:8080/";
	return (
		<BrowserRouter>
			<Routes>
				<Route path='/' element={<Login />} />
				<Route path='/SignUpDoctor' element={<SignUpDoctor />} />
				<Route path='/SignUpPatient' element={<SignUpPatient />} />
				<Route path='/dashboard' element={<Dashboard />} />
				<Route path='/home' element={<Home />} />
				{/* <Route path="/booking" element={<Booking />} /> */}
			</Routes>
		</BrowserRouter>
	);
}

export default App;
