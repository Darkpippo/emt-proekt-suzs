import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Dropdown } from 'react-bootstrap';

const AppointmentPaymentList = () => {
    const [appointments, setAppointments] = useState([]);
    const [patients, setPatients] = useState([]);
    const [payments, setPayments] = useState([]);
    const [selectedPatient, setSelectedPatient] = useState(null);
    const fetchAppointments = async () => {
        try {
            const response = await axios.get('http://localhost:9090/api/appointments');
            setAppointments(response.data);
        } catch (error) {
            console.error('Error fetching appointments:', error);
        }
    };

    const fetchPatients = async () => {
        try {
            const response = await axios.get('http://localhost:9093/api/patients');
            setPatients(response.data);
        } catch (error) {
            console.error('Error fetching patients:', error);
        }
    };

    const fetchPayments = async () => {
        try {
            const response = await axios.get('http://localhost:9091/api/payments');
            setPayments(response.data);
            console.log(response.data);
        } catch (error) {
            console.error('Error fetching payments:', error);
        }
    };

    useEffect(() => {
        fetchAppointments();
        fetchPatients();
        fetchPayments();
    }, []);

    const filteredAppointments = selectedPatient
        ? appointments.filter(appointment => appointment.patientId === selectedPatient)
        : appointments;

    const findPaymentForAppointment = (appointmentId) => {
        const payment = payments.find(payment => payment.appointmentId === appointmentId);
        return payment ? payment.status : 'No Payment';
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Appointments and Payments</h2>

            <Dropdown className="mb-3">
                <Dropdown.Toggle variant="primary">
                    {selectedPatient
                        ? patients.find(patient => patient.id === selectedPatient)?.name
                        : 'Filter by Patient'}
                </Dropdown.Toggle>
                <Dropdown.Menu>
                    <Dropdown.Item onClick={() => setSelectedPatient(null)}>All Patients</Dropdown.Item>
                    {patients.map(patient => (
                        <Dropdown.Item key={patient.id} onClick={() => setSelectedPatient(patient.id)}>
                            {patient.name}
                        </Dropdown.Item>
                    ))}
                </Dropdown.Menu>
            </Dropdown>

            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th>Appointment ID</th>
                    <th>Patient Name</th>
                    <th>Date & Time</th>
                    <th>Payment Status</th>
                </tr>
                </thead>
                <tbody>
                {filteredAppointments.map(appointment => {
                    const patient = patients.find(p => p.id === appointment.patientId);
                    const paymentStatus = findPaymentForAppointment(appointment.id);
                    return (
                        <tr key={appointment.id}>
                            <td>{appointment.id}</td>
                            <td>{patient ? patient.name : 'Unknown'}</td>
                            <td>{appointment.appointmentDate.appointmentDateTime}</td>
                            <td>{paymentStatus}</td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>
        </div>
    );
};

export default AppointmentPaymentList;
