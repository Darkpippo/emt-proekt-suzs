import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Table } from 'react-bootstrap';

const DoctorList = () => {
    const [doctors, setDoctors] = useState([]);

    useEffect(() => {
        const fetchDoctors = async () => {
            const response = await axios.get('http://localhost:9090/api/doctors');
            setDoctors(response.data);
        };
        fetchDoctors();
    }, []);

    return (
        <div className="container mt-5">
            <h2 className="mb-4">Doctors</h2>
            <Table striped bordered hover>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                </tr>
                </thead>
                <tbody>
                {doctors.map(doctor => (
                    <tr key={doctor.id}>
                        <td>{doctor.id}</td>
                        <td>{doctor.name}</td>
                        <td>{doctor.contactInformation.email}</td>
                        <td>{doctor.contactInformation.phoneNumber}</td>
                        <td>{doctor.contactInformation.address}</td>
                    </tr>
                ))}
                </tbody>
            </Table>
        </div>
    );
};

export default DoctorList;
