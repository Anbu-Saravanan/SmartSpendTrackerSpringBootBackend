# Event Management System - Backend (Spring Boot)

This is the backend API for the Event Management System built using Spring Boot and Java.

## Features

- User registration with email OTP verification
- Role-based access control (Admin/User)
- JWT Authentication with Refresh Token
- Expense/Income CRUD operations (Admin)
- Budget Management
- Budget Registration for Users
- Email Notifications/With Live Notifications
- 
- MongoDB support

## Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT
- MongoDB
- JavaMailSender
- Websocket Live Notifications
- Maven

## Getting Started

### Prerequisites

- Java 17
- Maven
- MongoDB if you're using it

### Running the Project

```bash
# Build the project
mvn clean install

# Run the app
mvn spring-boot:run


Default Creation of one Admin 
----------------------------
Email:anbu.saravanan11211@gmail.com
Password:admin

API Endpoints
POST /api/auth/register - Register new user

POST /api/auth/login - Login and receive JWT tokens
