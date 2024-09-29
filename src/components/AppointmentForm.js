import React, { useEffect, useState } from 'react';
import { Button, Form, Container } from 'react-bootstrap';
import { useNavigate, useParams } from "react-router-dom";
import axios from 'axios';

const AppointmentForm = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const [isEditing, setIsEditing] = useState(false);
    const [patients, setPatients] = useState([]);
    const [filteredPatients, setFilteredPatients] = useState([]);
    const [doctors, setDoctors] = useState([]);
    const [searchTerm, setSearchTerm] = useState('');
    const [appointment, setAppointment] = useState({
        patientId: '',
        date: '',
        time: '',
        doctor: {},
        status: 'reserved' // Default status
    });

    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const response = await axios.get('http://localhost:9093/api/patients');
                setPatients(response.data);
                setFilteredPatients(response.data);
            } catch (error) {
                console.error('Error fetching patients:', error);
            }
        };
        fetchPatients();
    }, []);

    useEffect(() => {
        const fetchDoctors = async () => {
            try {
                const response = await axios.get('http://localhost:9090/api/doctors');
                setDoctors(response.data);
            } catch (error) {
                console.error('Error fetching doctors:', error);
            }
        };
        fetchDoctors();
    }, []);

    useEffect(() => {
        const results = patients.filter(patient =>
            patient.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
            patient.insurance.policyNumber.includes(searchTerm)
        );
        setFilteredPatients(results);
    }, [searchTerm, patients]);

    useEffect(() => {
        if (id) {
            setIsEditing(true);
            const fetchAppointment = async () => {
                try {
                    const response = await axios.get(`http://localhost:9090/api/appointments/${id}`);
                    const { patientId, appointmentDate, doctor, status } = response.data;
                    const [date, time] = appointmentDate.appointmentDateTime.split('T');
                    setAppointment({
                        patientId,
                        date,
                        time,
                        doctor,
                        status
                    });
                } catch (error) {
                    console.error('Error fetching appointment:', error);
                }
            };
            fetchAppointment();
        }
    }, [id]);

    const handleChange = (e) => {
        const { name, value } = e.target;

        if (name === "doctorId") {
            const selectedDoctor = doctors.find(doctor => doctor.id.toString() === value);
            setAppointment((prev) => ({
                ...prev,
                doctor: selectedDoctor || {}
            }));
        } else {
            setAppointment((prev) => ({
                ...prev,
                [name]: value
            }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        const appointmentDateTime = `${appointment.date}T${appointment.time}`;
        const data = {
            patientId: appointment.patientId,
            appointmentDate: { appointmentDateTime },
            doctor: appointment.doctor,
            status: appointment.status
        };

        try {
            if (isEditing) {
                await axios.put(`http://localhost:9090/api/appointments/${id}`, data);
            } else {
                await axios.post('http://localhost:9090/api/appointments', data);
            }
            navigate('/appointments/patient');
        } catch (error) {
            console.error('Error creating/updating appointment:', error);
        }
    };

    return (
        <Container>
            <h2 className="my-4">{isEditing ? 'Edit Appointment' : 'Create Appointment'}</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formSearch">
                    <Form.Label>Search Patients</Form.Label>
                    <Form.Control
                        type="text"
                        placeholder="Enter name or policy number"
                        value={searchTerm}
                        onChange={(e) => setSearchTerm(e.target.value)}
                        disabled={isEditing}
                    />
                </Form.Group>

                <Form.Group controlId="formPatient">
                    <Form.Label>Select Patient</Form.Label>
                    {isEditing ? (
                        <Form.Control
                            type="text"
                            value={patients.find(patient => patient.id === appointment.patientId)?.name || ''}
                            readOnly
                        />
                    ) : (
                        <Form.Control
                            as="select"
                            name="patientId"
                            value={appointment.patientId}
                            onChange={handleChange}
                            required
                        >
                            <option value="">Select a patient</option>
                            {filteredPatients.map(patient => (
                                <option key={patient.id} value={patient.id}>
                                    {patient.name} - {patient.insurance.policyNumber}
                                </option>
                            ))}
                        </Form.Control>
                    )}
                </Form.Group>

                <Form.Group controlId="formDate">
                    <Form.Label>Date</Form.Label>
                    <Form.Control
                        type="date"
                        name="date"
                        value={appointment.date}
                        onChange={handleChange}
                        required
                        min={new Date().toISOString().split("T")[0]}
                    />
                </Form.Group>

                <Form.Group controlId="formTime">
                    <Form.Label>Time</Form.Label>
                    <Form.Control
                        type="time"
                        name="time"
                        value={appointment.time}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>

                <Form.Group controlId="formDoctor">
                    <Form.Label>Select Doctor</Form.Label>
                    <Form.Control
                        as="select"
                        name="doctorId"
                        value={appointment.doctor.id || ''}
                        onChange={handleChange}
                        required
                    >
                        <option value="">Select a doctor</option>
                        {doctors.map(doctor => (
                            <option key={doctor.id} value={doctor.id}>
                                {doctor.name}
                            </option>
                        ))}
                    </Form.Control>
                </Form.Group>

                <Form.Group controlId="formStatus">
                    <Form.Label>Status</Form.Label>
                    <Form.Control
                        as="select"
                        name="status"
                        value={appointment.status}
                        onChange={handleChange}
                    >
                        <option value="reserved">Reserved</option>
                        <option value="delayed">Delayed</option>
                        <option value="canceled">Canceled</option>
                    </Form.Control>
                </Form.Group>
                <Button variant="primary" type="submit" className="mt-4">
                    {isEditing ? 'Update Appointment' : 'Create Appointment'}
                </Button>
            </Form>
        </Container>
    );
};

export default AppointmentForm;
