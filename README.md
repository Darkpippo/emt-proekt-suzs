Indeks 213209 - Aleksandar Panovski

# Healthcare Appointment Management System (SUZS)

## Project Structure

1. **Appointment Scheduling Service** (Port: 9090)
2. **Patient Management Service** (Port: 9093)
3. **Billing and Payment Service** (Port: 9091)

## Setup Instructions

### Prerequisites
- **Java 17** or higher
- **Maven** (for building the project)
- **PostgreSQL** (with databases set up for each service)
- **Node.js** and **npm** (for the React frontend)
- **Git** (to clone the repository)

### Database Setup

You need to set up PostgreSQL databases for each service. The services are configured to connect to the following databases:

1. **Appointment Scheduling Service**  
   - Database: `appointment_scheduling`  
   - Port: `5432`  
   - Application Port: `9090`

2. **Patient Management Service**  
   - Database: `patient_management`  
   - Port: `5432`  
   - Application Port: `9093`

3. **Billing and Payment Service**  
   - Database: `billing_payment`  
   - Port: `5432`  
   - Application Port: `9091`

4. **Shared-kernel**
   - Application Port: `8000`
