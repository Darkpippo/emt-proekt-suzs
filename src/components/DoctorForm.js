import React, { useState } from 'react';
import { Button, Col, Container, Form, Row } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const DoctorForm = () => {
    const navigate = useNavigate();
    const [doctor, setDoctor] = useState({
        name: '',
        contactInformation: {
            email: '',
            phoneNumber: '',
            address: '',
        },
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setDoctor((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleContactChange = (e) => {
        const { name, value } = e.target;
        setDoctor((prev) => ({
            ...prev,
            contactInformation: {
                ...prev.contactInformation,
                [name]: value,
            },
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:9090/api/doctors', doctor);
            navigate('/doctors');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <Container>
            <h2 className="my-4">Create Doctor</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control
                        type="text"
                        name="name"
                        value={doctor.name}
                        onChange={handleChange}
                        required
                    />
                </Form.Group>

                <h3 className="mt-4">Contact Information</h3>
                <Row>
                    <Col>
                        <Form.Group controlId="formEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control
                                type="email"
                                name="email"
                                value={doctor.contactInformation.email}
                                onChange={handleContactChange}
                                required
                            />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group controlId="formPhone">
                            <Form.Label>Phone</Form.Label>
                            <Form.Control
                                type="tel"
                                name="phoneNumber"
                                value={doctor.contactInformation.phoneNumber}
                                onChange={handleContactChange}
                                required
                            />
                        </Form.Group>
                    </Col>
                </Row>
                <Form.Group controlId="formAddress">
                    <Form.Label>Address</Form.Label>
                    <Form.Control
                        type="text"
                        name="address"
                        value={doctor.contactInformation.address}
                        onChange={handleContactChange}
                        required
                    />
                </Form.Group>

                <Button variant="primary" type="submit" className="mt-4">
                    Create Doctor
                </Button>
            </Form>
        </Container>
    );
};

export default DoctorForm;
