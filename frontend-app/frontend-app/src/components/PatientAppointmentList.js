import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const PatientAppointmentList = () => {
    const [appointments, setAppointments] = useState([]);
    const navigate = useNavigate();

    const fetchAppointments = async () => {
        try {
            const response = await axios.get(`http://localhost:9090/api/appointments`); // Fetch appointments
            setAppointments(response.data);
        } catch (error) {
            console.error("Error fetching appointments:", error);
        }
    };

    useEffect(() => {
        fetchAppointments();
    }, []);

    const handleEdit = (appointment) => {
        navigate(`/appointments/edit/${appointment.id}`, { state: { appointment } });
    };

    const handlePayment = async (appointment) => {
        try {
            if (appointment.status === 'paid') {
                alert('Payment has already been made for this appointment.');
                return;
            }
            const response = await axios.get(`http://localhost:9091/api/payments/${appointment.id}`);
            alert('Payment successful!');
            fetchAppointments();
        } catch (error) {
            console.error('Error processing payment:', error);
            alert('Payment failed. Please try again.');
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Patient Appointment's</h2>
            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Doctor</th>
                    <th>Date & Time</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {appointments.map(appointment => (
                    <tr key={appointment.id}>
                        <td>{appointment.id}</td>
                        <td>{appointment.doctor ? appointment.doctor.name : 'Unknown'}</td>
                        <td>{appointment.appointmentDate.appointmentDateTime}</td>
                        <td>{appointment.status}</td>
                        <td>
                            <Button variant="warning" onClick={() => handleEdit(appointment)}>Edit</Button>
                            <Button
                                variant="info"
                                onClick={() => handlePayment(appointment)}
                                disabled={appointment.status === 'paid'} // Disable button if already paid
                            >
                                Pay
                            </Button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default PatientAppointmentList;
