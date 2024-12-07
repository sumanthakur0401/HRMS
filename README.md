# ![Logo](link-to-logo) HRMS - Human Resource Management System  

Welcome to the **Human Resource Management System (HRMS)**! This project is built to simplify and streamline HR processes by managing employees, departments, salaries, titles, and department managers with a well-structured relational database.

---

## 🚀 Features

- **Core Technologies Used**:
  - Java Spring Boot  
  - MySQL  
  - Hibernate  
  - Swagger  
  - Postman  
  - SonarQube  

- **Key Functionalities**:
  - Comprehensive management of employees, departments, and their relationships.
  - Real-world mapping of relational data using:
    - **One-to-One** relationships.
    - **One-to-Many** relationships.
    - **Many-to-One** relationships.
  - REST API testing and documentation using **Postman** and **Swagger**.
  - Code quality and security checks using **SonarQube**.

---

## 🗄️ Database Design

The HRMS system includes **6 primary tables** with well-defined relationships:

1. **Employee**
2. **Department**
3. **EmpDept**  
4. **Salary**  
5. **Titles**  
6. **DeptManager**  

### Relationship Overview:
- **Employee ↔ Department**: One-to-Many  
- **Employee ↔ EmpDept**: Many-to-One  
- **Employee ↔ Salary**: One-to-One  
- **Employee ↔ Titles**: Many-to-Many  
- **Department ↔ DeptManager**: One-to-Many  

![Database Schema](link-to-database-schema-image)

---

## 📂 Project Structure

