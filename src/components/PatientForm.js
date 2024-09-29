import React, { useState } from 'react';
import axios from 'axios';
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useNavigate } from 'react-router-dom';

const PatientForm = () => {
    const navigate = useNavigate();
    const [patient, setPatient] = useState({
        name: '',
        contactInformation: {
            email: '',
            phoneNumber: '',
            address: ''
        },
        medicalRecord: {
            diagnoses: '',
            treatments: '',
            medications: '',
        },
        insurance: {
            policyNumber: '',
            coverageType: '',
            expiryDate: '',
        },
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setPatient((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleContactChange = (e) => {
        const { name, value } = e.target;
        setPatient((prev) => ({
            ...prev,
            contactInformation: {
                ...prev.contactInformation,
                [name]: value,
            },
        }));
    };

    const handleMedicalChange = (e) => {
        const { name, value } = e.target;
        setPatient((prev) => ({
            ...prev,
            medicalRecord: {
                ...prev.medicalRecord,
                [name]: value,
            },
        }));
    };

    const handleInsuranceChange = (e) => {
        const { name, value } = e.target;
        setPatient((prev) => ({
            ...prev,
            insurance: {
                ...prev.insurance,
                [name]: value,
            },
        }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:9093/api/patients', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(patient),
            });
            navigate('/patients');
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <Container>
            <h2 className="my-4">Create Patient</h2>
            <Form onSubmit={handleSubmit}>
                <Form.Group controlId="formName">
                    <Form.Label>Name</Form.Label>
                    <Form.Control type="text" name="name" value={patient.name} onChange={handleChange} required />
                </Form.Group>

                <h3 className="mt-4">Contact Information</h3>
                <Row>
                    <Col>
                        <Form.Group controlId="formEmail">
                            <Form.Label>Email</Form.Label>
                            <Form.Control type="email" name="email" value={patient.contactInformation.email} onChange={handleContactChange} required />
                        </Form.Group>
                    </Col>
                    <Col>
                        <Form.Group controlId="formPhone">
                            <Form.Label>Phone</Form.Label>
                            <Form.Control type="tel" name="phoneNumber" value={patient.contactInformation.phoneNumber} onChange={handleContactChange} required /> {/* Updated here */}
                        </Form.Group>
                    </Col>
                </Row>
                <Form.Group controlId="formAddress">
                    <Form.Label>Address</Form.Label>
                    <Form.Control type="text" name="address" value={patient.contactInformation.address} onChange={handleContactChange} required /> {/* Added address field */}
                </Form.Group>

                <h3 className="mt-4">Medical Record</h3>
                <Form.Group controlId="formDiagnoses">
                    <Form.Label>Diagnoses</Form.Label>
                    <Form.Control type="text" name="diagnoses" value={patient.medicalRecord.diagnoses} onChange={handleMedicalChange} required />
                </Form.Group>
                <Form.Group controlId="formTreatments">
                    <Form.Label>Treatments</Form.Label>
                    <Form.Control type="text" name="treatments" value={patient.medicalRecord.treatments} onChange={handleMedicalChange} required />
                </Form.Group>
                <Form.Group controlId="formMedications">
                    <Form.Label>Medications</Form.Label>
                    <Form.Control type="text" name="medications" value={patient.medicalRecord.medications} onChange={handleMedicalChange} required />
                </Form.Group>

                <h3 className="mt-4">Insurance</h3>
                <Form.Group controlId="formPolicyNumber">
                    <Form.Label>Policy Number</Form.Label>
                    <Form.Control type="text" name="policyNumber" value={patient.insurance.policyNumber} onChange={handleInsuranceChange} required />
                </Form.Group>
                <Form.Group controlId="formCoverageType">
                    <Form.Label>Coverage Type</Form.Label>
                    <Form.Control type="text" name="coverageType" value={patient.insurance.coverageType} onChange={handleInsuranceChange} required />
                </Form.Group>
                <Form.Group controlId="formExpiryDate">
                    <Form.Label>Expiry Date</Form.Label>
                    <Form.Control type="date" name="expiryDate" value={patient.insurance.expiryDate} onChange={handleInsuranceChange} required />
                </Form.Group>

                <Button variant="primary" type="submit" className="mt-4">Create Patient</Button>
            </Form>
        </Container>
    );
};

export default PatientForm;
