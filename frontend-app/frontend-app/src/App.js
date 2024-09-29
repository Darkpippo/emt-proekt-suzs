import logo from './logo.svg';
import './App.css';
import NavbarComponent from "./components/NavbarComponent";
import AppointmentList from "./components/AppointmentList";
import DoctorAppointmentList from "./components/DoctorAppoinmentList";
import DoctorForm from "./components/DoctorForm";
import PatientAppointmentList from "./components/PatientAppointmentList";
import AppointmentForm from "./components/AppointmentForm";
import PatientList from "./components/PatientList";
import PatientForm from "./components/PatientForm";
import DoctorList from "./components/DoctorList";
import AppointmentPaymentList from "./components/AppointmentPaymentList";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';

const App = () => {
  return (
      <Router>
        <NavbarComponent />
        <div className="container">
          <Routes>
            <Route path="/" element={<h1>Welcome to the Healthcare App</h1>} />
            <Route path="/appointments" element={<AppointmentList />} />
            <Route path="/appointments/doctor" element={<DoctorAppointmentList />} />
            <Route path="/appointments/doctor/create" element={<DoctorForm />} />
            <Route path="/appointments/patient" element={<PatientAppointmentList />} />
            <Route path="/appointments/create" element={<AppointmentForm />} />
            <Route path="/appointments/edit/:id" element={<AppointmentForm />} />
            <Route path="/appointments/edit/:id" element={<AppointmentForm mode="doctor" />} />

            <Route path="/patients" element={<PatientList />} />
            <Route path="/patients/new" element={<PatientForm />} />
            <Route path="/doctors" element={<DoctorList />} />
            <Route path="/payments" element={<AppointmentPaymentList />} />
          </Routes>
        </div>
      </Router>
  );
};

export default App;