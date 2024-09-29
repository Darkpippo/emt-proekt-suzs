import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table, Button, Modal, Form } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

const DoctorAppointmentList = () => {
    const [appointments, setAppointments] = useState([]);
    const [patients, setPatients] = useState({});
    const [showModal, setShowModal] = useState(false);
    const [selectedAppointment, setSelectedAppointment] = useState(null);
    const navigate = useNavigate();

    const fetchAppointments = async () => {
        try {
            const response = await axios.get('http://localhost:9090/api/appointments');
            setAppointments(response.data);
        } catch (error) {
            console.error("Error fetching appointments:", error);
        }
    };

    const fetchPatients = async () => {
        try {
            const response = await axios.get('http://localhost:9093/api/patients');
            const patientMap = response.data.reduce((acc, patient) => {
                acc[patient.id] = patient;
                return acc;
            }, {});
            setPatients(patientMap);
        } catch (error) {
            console.error("Error fetching patients:", error);
        }
    };

    useEffect(() => {
        fetchAppointments();
        fetchPatients();
    }, []);

    const handleEditClick = (appointment) => {
        setSelectedAppointment(appointment);
        setShowModal(true);
    };

    const handleClose = () => {
        setShowModal(false);
        setSelectedAppointment(null);
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setSelectedAppointment((prev) => ({
            ...prev,
            [name]: value
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!selectedAppointment) return;

        const { id, appointmentDate } = selectedAppointment;
        const updatedDateTime = `${appointmentDate.appointmentDateTime.split('T')[0]}T${selectedAppointment.appointmentTime}`;

        try {
            await axios.put(`http://localhost:9090/api/appointments/${id}`, {
                appointmentDate: {
                    appointmentDateTime: updatedDateTime
                },
                status: selectedAppointment.status,
            });
            fetchAppointments();
            handleClose();
        } catch (error) {
            console.error("Error updating appointment:", error);
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Doctor's Appointments</h2>

            <Table striped bordered hover responsive>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Patient</th>
                    <th>Insurance</th>
                    <th>Medical Record - Diagnoses</th>
                    <th>Medical Record - Treatments</th>
                    <th>Medical Record - Medications</th>
                    <th>Date & Time</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {appointments.map(appointment => {
                    const patient = patients[appointment.patientId];
                    return (
                        <tr key={appointment.id}>
                            <td>{appointment.id}</td>
                            <td>{patient ? patient.name : 'Unknown'}</td>
                            <td>{patient?.insurance?.policyNumber || 'N/A'}</td>
                            <td>{patient?.medicalRecord?.diagnoses || 'N/A'}</td>
                            <td>{patient?.medicalRecord?.treatments || 'N/A'}</td>
                            <td>{patient?.medicalRecord?.medications || 'N/A'}</td>
                            <td>{appointment.appointmentDate.appointmentDateTime}</td>
                            <td>{appointment.status}</td>
                            <td>
                                <Button variant="warning" onClick={() => handleEditClick(appointment)}>Edit</Button>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </Table>

            <Modal show={showModal} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>Edit Appointment</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    {selectedAppointment && (
                        <Form onSubmit={handleSubmit}>
                            <Form.Group controlId="formDate">
                                <Form.Label>Date</Form.Label>
                                <Form.Control
                                    type="date"
                                    name="appointmentDate"
                                    value={selectedAppointment.appointmentDate.appointmentDateTime.split('T')[0]}
                                    onChange={handleChange}
                                    required
                                />
                            </Form.Group>

                            <Form.Group controlId="formTime">
                                <Form.Label>Time</Form.Label>
                                <Form.Control
                                    type="time"
                                    name="appointmentTime"
                                    value={selectedAppointment.appointmentDate.appointmentDateTime.split('T')[1] || ''}
                                    onChange={handleChange}
                                    required
                                />
                            </Form.Group>

                            <Form.Group controlId="formStatus">
                                <Form.Label>Status</Form.Label>
                                <Form.Control
                                    as="select"
                                    name="status"
                                    value={selectedAppointment.status}
                                    onChange={handleChange}
                                    required
                                >
                                    <option value="Reserved">Reserved</option>
                                    <option value="Delayed">Delayed</option>
                                    <option value="Canceled">Canceled</option>
                                </Form.Control>
                            </Form.Group>

                            <Button variant="primary" type="submit">
                                Update Appointment
                            </Button>
                        </Form>
                    )}
                </Modal.Body>
            </Modal>
        </div>
    );
};

export default DoctorAppointmentList;
