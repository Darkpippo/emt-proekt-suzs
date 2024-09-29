import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';

const PatientList = () => {
    const [patients, setPatients] = useState([]);

    useEffect(() => {
        const fetchPatients = async () => {
            try {
                const response = await axios.get('http://localhost:9093/api/patients');
                console.log(response.data); // This will log the fetched data
                setPatients(response.data); // Set the fetched data to the state
            } catch (error) {
                console.error('Error fetching patients:', error);
            }
        };
        fetchPatients();
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Patients</h2>
            <Table striped bordered hover responsive className="table-sm">
                <thead className="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Insurance Policy Number</th>
                    <th>Insurance Coverage</th>
                    <th>Insurance Expiry Date</th>
                    <th>Medical Diagnoses</th>
                    <th>Medical Treatments</th>
                    <th>Medications</th>
                </tr>
                </thead>
                <tbody>
                {patients.map(patient => (
                    <tr key={patient.id}>
                        <td>{patient.id}</td>
                        <td>{patient.name}</td>
                        <td>{patient.contactInformation.email}</td>
                        <td>{patient.contactInformation.phoneNumber}</td>
                        <td>{patient.contactInformation.address}</td>
                        <td>{patient.insurance?.policyNumber}</td>
                        <td>{patient.insurance?.coverageType}</td>
                        <td>{patient.insurance?.expiryDate}</td>
                        <td>{patient.medicalRecord?.diagnoses}</td>
                        <td>{patient.medicalRecord?.treatments}</td>
                        <td>{patient.medicalRecord?.medications}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default PatientList;
