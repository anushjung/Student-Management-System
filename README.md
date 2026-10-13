# Student Management System

A Java-based Student Management System built while learning Java and preparing for a Java internship.

The project started as a console-based application and has been expanded with a Java Swing graphical user interface and MySQL database integration.

## Features

### Student Management
- Add, view, search, update, and delete student records
- Search students by ID and name
- Sort student records
- Display student statistics
- Display course statistics
- Validate student ID, age, name, and required fields

### Graphical User Interface
- Desktop interface built with Java Swing
- Display student records in a `JTable`
- Select a table row to load student details into the form
- Add and update student records through the GUI
- Delete student records with a confirmation dialog
- Search students by name through the GUI
- Improved layout and styling

### Database
- Store student records in MySQL
- Connect Java to MySQL using JDBC
- Keep database operations in a DAO class
- Read database credentials from environment variables

## Screenshots

Add screenshots of your running application to the `screenshots/` folder in this repository. Use your own screenshots and avoid including passwords or other private information.

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Java Swing
- JDBC
- MySQL
- IntelliJ IDEA
- Git and GitHub

## Project Structure

```text
StudentManagementSystem/
├── screenshots/
│   └── main-gui.png
├── src/
│   ├── DatabaseConnection.java
│   ├── Main.java
│   ├── Student.java
│   ├── StudentDAO.java
│   ├── StudentManagementGUI.java
│   └── StudentManager.java
├── .gitignore
└── README.md
```

## Database Setup

The application uses a MySQL database named `student_management` and a table named `students`.

Run this SQL in MySQL Workbench:

```sql
CREATE DATABASE student_management;

USE student_management;

CREATE TABLE students (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    address VARCHAR(150) NOT NULL,
    course VARCHAR(100) NOT NULL
);
```

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/anushjung/Student-Management-System.git
```

### 2. Open the project

Open the cloned project in IntelliJ IDEA.

### 3. Configure MySQL

Make sure MySQL Server is running, then create the database and table using the SQL above.

### 4. Add MySQL Connector/J

Add the MySQL Connector/J library to the project so JDBC can connect to MySQL.

### 5. Configure database credentials

In the IntelliJ run configuration, set these environment variables:

```text
DB_USER=your_mysql_username
DB_PASSWORD=your_mysql_password
```

Replace the example values with your own local credentials. The application expects these environment variables to be configured before it connects to the database.

**Security:** Never put your actual database password in source code, README files, screenshots, or GitHub commits.

### 6. Run the application

- Run `StudentManagementGUI.java` to launch the graphical application.
- Run `Main.java` to launch the console application.

## What I Learned

- Applying object-oriented programming in Java
- Working with `ArrayList` and Collections
- Implementing CRUD operations
- Connecting Java to MySQL using JDBC
- Separating database operations with a DAO class
- Building desktop interfaces with Java Swing
- Displaying database records in a `JTable`
- Validating user input
- Using Git and GitHub for version control

## Why I Built This Project

I built this project to practise Java by creating a practical application instead of only following tutorials. I have been adding features step by step and using Git and GitHub to track the project's development.

My goal is to strengthen my Java fundamentals and build a portfolio for Java internship opportunities.

## Project Status

The core console application, Swing interface, and MySQL integration have been implemented. I will continue improving the project as I learn more about Java development.
