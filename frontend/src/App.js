import { Route, Routes } from "react-router-dom";
import Login from "./components/Dashboard/Login/Login";
import DoctorDashboard from "./components/Dashboard/DoctorDashboard";
import axios from "axios";
import SignUpDoctor from "./components/Dashboard/SignUp/SignUpDoctor";
import SignUpPatient from "./components/Dashboard/SignUp/SignUpPatient";

function App() {
  axios.defaults.baseURL = "http://localhost:8080/";
  return (
    <Routes>
      <Route exact path="/" element={<Login />} />
      <Route path="/SignUpDoctor" element={<SignUpDoctor />} />
      <Route path="/SignUpPatient" element={<SignUpPatient />} />
      <Route exact path="/dash" element={<DoctorDashboard />} />
    </Routes>
  );
}

export default App;
