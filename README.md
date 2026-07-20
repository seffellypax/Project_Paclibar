FINAL PROJECT
By: July Seffelly P. Paclibar

This is a JavaFX-based E-Wallet application designed to manage user finances securely with persistent session management.

🚀 Features
Persistent User Sessions: Uses Java Serialization to keep users logged in across application restarts.

Secure Authentication: Handles login and registration with integrated database connectivity.

Financial Management: Allows users to track balances, make transfers, and deposit funds.

🛠️ Technical Implementation
1. Dependency Inversion Principle (DIP)
   This project ensures that high-level controllers do not depend on low-level database implementations. By introducing the IUserDAO interface, the system depends on abstractions rather than concrete classes, making the code more modular and easier to maintain.

2. Single Responsibility Principle (SRP)
   The session logic is isolated within the SessionManager class. This class has only one responsibility: managing the lifecycle (save, retrieve, delete) of the session.dat file. This prevents session logic from cluttering the UI or database controllers.

💻 How to Run
Ensure you have JavaFX and the MySQL Connector set up in your IDE.

Run the Launcher class to start the application.

The application will automatically check for a session.dat file to determine if it should show the Login screen or jump straight to the Dashboard.