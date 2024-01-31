


# Blood Bank Management System (Java)

## Introduction

This project is a Blood Bank Management System implemented in Java using IntelliJ IDEA. The system allows users to donate blood, request blood, and register as donors. There is a separate admin login to manage the system.

## Setup Instructions

### 1. Import the Database

- Locate the `database` folder in the project.
- Import the provided SQL file (`blood_bank_management_system.sql`) into your MySQL server to create the necessary database and tables.

### 2. Configure Database Connection

- Open the project in IntelliJ IDEA.
- Navigate to the `src/db.java` file.
- Update the following fields in the `db.java` file with your MySQL server details:
  ```java
  String url = "jdbc:mysql://your_database_url:your_port/bloodbank";
  String username = "your_username";
  String password = "your_password";
  ```

### 3. Set Up the Project in IntelliJ IDEA

- Open IntelliJ IDEA.
- Navigate to `File` > `Open` and select the project folder.
- Ensure that you have the necessary SDK installed. Configure the SDK in the project settings if needed.

### 4. Locate the Main Class

- Open the `src/mainpage.java` file.

### 5. Run the Application

- Right-click on the `mainpage.java` file and select "Run MainPage.main()".
- The Blood Bank Management System should start, and you can interact with it through the  GUI.

## Features

- **Donation Management**: Register as a donor and donate blood.
- **Blood Request**: Request blood when needed.
- **Donor Registration**: Users can register as donors.
- **Admin Login**: Separate login for administrators to manage the system.

## Contributors

- Abdul Hannan

