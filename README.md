# Employee Management Module

## Project Overview

This project is a RESTful backend application built using Java 17, Spring Boot 3, and MySQL.

The main goal of this system is to manage employees and departments along with reporting manager relationships. It maintains proper relational mapping using JPA (Hibernate) and ensures referential integrity through database constraints and service-level validations.

The application also includes a global request–response logging middleware to monitor API activity and performance.

The project follows a clean layered architecture for better readability, maintainability, and separation of concerns.

---

## Tech Stack Used

- Java 17  
- Spring Boot 3  
- Spring Data JPA (Hibernate)  
- MySQL  
- Maven  
- SLF4J Logging  

---

## Architecture Overview

The project is structured using layered architecture:

Controller → Service → Repository → Database  
                     ↓  
                   Mapper → DTO  

Each layer has a clear responsibility:

- Controller – Handles REST API requests  
- Service – Implements business logic  
- Repository – Handles database operations using JPA  
- Mapper – Converts Entity objects to DTO responses  
- DTO – Defines request and response structure  
- Exception Handler – Manages global error handling  
- Filter – Implements request–response logging middleware  


---

## Database Design

### 1️⃣ Department Table

| Column | Description |
|--------|------------|
| id | Primary Key |
| name | Unique department name |

Each department name must be unique.

---

### 2️⃣ Employee Table

| Column | Description |
|--------|------------|
| id | Primary Key |
| first_name | Employee first name |
| last_name | Employee last name |
| email | Unique email |
| department_id | Foreign Key to Department |
| manager_id | Self-referencing Foreign Key (nullable) |
| created_at | Creation timestamp |
| updated_at | Update timestamp |

### Relationships:

- One employee belongs to one department.
- One employee may have one manager.
- One manager can have multiple subordinates.

---

## 🌐 REST API Endpoints

### Employee APIs

Create Employee  
POST /api/employees  

Get All Employees  
GET /api/employees  

Get Employee By ID  
GET /api/employees/{id}  

Update Employee  
PUT /api/employees/{id}  

Delete Employee  
DELETE /api/employees/{id}  

---

### Department APIs

Create Department  
POST /api/departments  

Get All Departments  
GET /api/departments  

Delete Department  
DELETE /api/departments/{id}  
(Deletion blocked if employees exist)

---

## 📷 API Testing (Postman)

All APIs were tested using Postman.

<img width="1753" height="880" alt="image" src="https://github.com/user-attachments/assets/1d95766b-13ce-4110-91d6-ab9d274decc0" />

## Postaman Collection





## 📝 Middleware Implementation

A global request–response logging middleware is implemented using:

The middleware logs:

- HTTP Method  
- API Endpoint  
- Response Status Code  
- Total Response Time  

Example log output:

POST /api/employees | Status: 200 | Time: 45ms

---

## ❗ Exception Handling

Global exception handling is implemented using:

@RestControllerAdvice

Handled scenarios:

- Resource not found → 404  
- Business rule violation → 400  
- Internal server error → 500  


---

## ▶ How To Run The Project

### Step 1: Create MySQL Database

CREATE DATABASE employee_db;

---

### Step 2: Configure application.properties

Update your MySQL username and password

---

### Step 3: Run Application

Using Maven:

mvn spring-boot:run

Or run from IDE as Spring Boot application.

---

## 🧪 Testing Flow

1. Create a department  
2. Create an employee without manager  
3. Create another employee with manager  
4. Fetch all employees  
5. Try deleting a manager and verify subordinates  
6. Try deleting department with employees (should fail)

---

## 👨‍💻 Author

Vaibhav Chougule  
Java Backend Developer
