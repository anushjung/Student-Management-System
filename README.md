# Student Management System

This is a Student Management System that I am building while learning Java.

I started this project as a simple console-based application and have been adding new features step by step. The project now uses MySQL for storing student data and Java Swing for the graphical user interface.

## Features

- Add a student
- View students
- Search students
- Update student information
- Delete students
- Sort students
- Show student statistics
- Show course statistics
- Store student data in MySQL
- Connect Java to MySQL using JDBC
- Display students using a Swing `JTable`
- Select a student from the table and load their details into the form
- Search students by name from the GUI
- Select a student from the table and load their details into the form
- Update student information from the GUI
- Delete students from the GUI with confirmation
- Input validation for student ID, age, and name

## Technologies Used

- Java
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- Git
- GitHub

## Project Structure

```text
StudentManagementSystem
│
├── src
│   ├── DatabaseConnection.java
│   ├── Main.java
│   ├── Student.java
│   ├── StudentDAO.java
│   ├── StudentManagementGUI.java
│   └── StudentManager.java
│
├── .gitignore
└── README.md
```

## Database

The project uses a MySQL database called:

```text
student_management
```

The main table is:

```text
students
```

The table contains:

- ID
- Name
- Age
- Address
- Course

### Table Structure

```sql
CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(150) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

## Application Structure

The application currently follows this basic flow:

```text
Java Swing GUI
       ↓
StudentManager / StudentDAO
       ↓
JDBC
       ↓
MySQL
```

The console version of the application is also kept in the project while the Swing GUI is being developed.

## Current Progress

### Completed

- [x] Basic Java console application
- [x] Object-Oriented Programming
- [x] ArrayList and Collections
- [x] File handling
- [x] MySQL database setup
- [x] JDBC connection
- [x] Database CRUD operations
- [x] Basic Swing GUI
- [x] Student form
- [x] JTable for displaying students
- [x] Table row selection
- [x] Add student from GUI
- [x] Update student from GUI
- [x] Delete student from GUI
- [x] Search students from GUI
- [x] Basic input validation
- [x] Improve GUI design
- [x] Make database configuration more secure

### Planned Improvements

- [ ] Add Maven
- [ ] Add testing
- [ ] Improve project structure
- [ ] Add better error handling
- [ ] Add logging

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/anushjung/Student-Management-System.git
```

### 2. Open the project

Open the project in IntelliJ IDEA.

### 3. Set up MySQL

Create the database:

```sql
CREATE DATABASE student_management;
```

Then create the `students` table:

```sql
USE student_management;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(150) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

### 4. Configure the database connection

Update the database connection settings in:

```text
DatabaseConnection.java
```

Use your own local MySQL username and password.

**Do not commit your actual database password to GitHub.**

### 5. Run the application

Run:

```text
StudentManagementGUI.java
```

The Swing application will open and allow you to manage students.

## Why I Made This Project

I made this project to get more comfortable with Java by building something practical instead of only following tutorials.
I am adding features one at a time and using Git and GitHub to keep track of how the project develops.

The goal is to continue improving this project and eventually use it as part of my Java internship portfolio.

## Project Status

This project is still under development. New features and improvements will be added gradually.
