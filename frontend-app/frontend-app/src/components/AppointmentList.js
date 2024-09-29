import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Dropdown } from 'react-bootstrap';

const AppointmentPaymentList = () => {
    const [appointments, setAppointments] = useState([]);
    const [payments, setPayments] = useState([]);
    const [patients, setPatients] = useState({});
    const [filteredAppointments, setFilteredAppointments] = useState([]);

    const fetchAppointments = async () => {
        try {
            const response = await axios.get('http://localhost:9090/api/appointments');
            setAppointments(response.data);
        } catch (error) {
            console.error("Error fetching appointments:", error);
        }
    };

    const fetchPayments = async () => {
        try {
            const response = await axios.get('http://localhost:9091/api/payments');
            setPayments(response.data);
        } catch (error) {
            console.error("Error fetching payments:", error);
        }
    };

    const fetchPatient = async (patientId) => {
        try {
            const response = await axios.get(`http://localhost:9093/api/patients/${patientId}`);
            return response.data;
        } catch (error) {
            console.error(`Error fetching patient with ID ${patientId}:`, error);
            return null;
        }
    };

    const fetchAllPatients = async () => {
        const patientPromises = appointments.map(appointment =>
            fetchPatient(appointment.patientId)
        );
        const patientDataArray = await Promise.all(patientPromises);
        const patientMap = {};
        patientDataArray.forEach(patient => {
            if (patient) {
                patientMap[patient.id] = patient;
            }
        });
        setPatients(patientMap);
    };

    useEffect(() => {
        fetchAppointments();
        fetchPayments();
    }, []);

    useEffect(() => {
        if (appointments.length > 0) {
            fetchAllPatients();
        }
    }, [appointments]);

    const matchPaymentToAppointment = (appointment) => {
        return payments.find(payment => payment.date === appointment.appointmentDate.appointmentDateTime);
    };

    // Handle filtering based on selected patient or doctor
    const handleFilterChange = (filterType, filterValue) => {
        if (filterType === 'doctor') {
            setFilteredAppointments(appointments.filter(app => app.doctor && app.doctor.id === filterValue));
        } else if (filterType === 'patient') {
            setFilteredAppointments(appointments.filter(app => app.patientId === filterValue));
        } else {
            setFilteredAppointments(appointments);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Appointments and Payments</h2>

            {/* Dropdown for filtering */}
            <div className="mb-4 container">
                <Dropdown>
                    <Dropdown.Toggle variant="secondary">
                        Filter by Doctor
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        <Dropdown.Item onClick={() => handleFilterChange('all', null)}>All Doctors</Dropdown.Item>
                        {appointments.map(app => (
                            app.doctor && <Dropdown.Item key={app.doctor.id} onClick={() => handleFilterChange('doctor', app.doctor.id)}>{app.doctor.name}</Dropdown.Item>
                        ))}
                    </Dropdown.Menu>
                </Dropdown>

                <Dropdown className="ml-3">
                    <Dropdown.Toggle variant="secondary">
                        Filter by Patient
                    </Dropdown.Toggle>
                    <Dropdown.Menu>
                        <Dropdown.Item onClick={() => handleFilterChange('all', null)}>All Patients</Dropdown.Item>
                        {Object.keys(patients).map(patientId => (
                            <Dropdown.Item key={patientId} onClick={() => handleFilterChange('patient', patientId)}>
                                {patients[patientId] ? patients[patientId].firstName: 'N/A'}
                            </Dropdown.Item>
                        ))}
                    </Dropdown.Menu>
                </Dropdown>
            </div>

            {/* Table to display appointments and payments */}
            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Doctor</th>
                    <th>Patient</th>
                    <th>Date & Time</th>
                    <th>Payment Status</th>
                </tr>
                </thead>
                <tbody>
                {(filteredAppointments.length > 0 ? filteredAppointments : appointments).map(appointment => {
                    const payment = matchPaymentToAppointment(appointment);
                    return (
                        <tr key={appointment.id}>
                            <td>{appointment.id}</td>
                            <td>{appointment.doctor ? appointment.doctor.name : 'Unknown'}</td>
                            <td>{patients[appointment.patientId] ? patients[appointment.patientId].firstName: 'N/A'}</td>
                            <td>{appointment.appointmentDate.appointmentDateTime}</td>
                            <td>{payment ? payment.status : 'No Payment'}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
};

export default AppointmentPaymentList;
