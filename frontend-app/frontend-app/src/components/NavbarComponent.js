import React from 'react';
import { Navbar, Nav } from 'react-bootstrap';

const NavbarComponent = () => {
    return (
        <Navbar bg="dark" variant="dark" expand="lg" className="shadow-sm py-3">
            <div className="container">
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="ml-auto">
                        <Nav.Link href="/appointments/doctor" className="nav-item-custom">Doctor's Appointments</Nav.Link>
                        <Nav.Link href="/appointments/patient" className="nav-item-custom">Patient's Appointments</Nav.Link>
                        <Nav.Link href="/appointments/create" className="nav-item-custom">Create Appointment</Nav.Link>

                        <Nav.Link href="/patients" className="nav-item-custom">Patients</Nav.Link>
                        <Nav.Link href="/patients/new" className="nav-item-custom">Add Patient</Nav.Link>
                        <Nav.Link href="/doctors" className="nav-item-custom">Doctors</Nav.Link>
                        <Nav.Link href="/appointments/doctor/create" className="nav-item-custom">Create Doctor</Nav.Link>
                        <Nav.Link href="/payments" className="nav-item-custom">Payments</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </div>
        </Navbar>
    );
};

export default NavbarComponent;
