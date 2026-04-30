# 🏨 Hotel Data System

A comprehensive, console-based Hotel Management Application built with pure Java. This project demonstrates strong Object-Oriented Programming (OOP) principles, custom architecture, and data persistence without relying on external databases.

## 🚀 Key Features

*   **Custom CLI Architecture:** Built using the Command Pattern. Features nested menus, graceful shutdown, and immediate input validation.
*   **Data Persistence:** Automatic binary serialization of system state (Rooms, Guests, Bookings) to `.data` files.
*   **Smart Startup Modes:** Users can boot from saved files, start with a clean slate, or generate a populated mock database.
*   **Role-Based Access:** Functionality is logically divided into three distinct modules:
    *   **Guest:** Registration, booking creation, and personal booking management.
    *   **Manager:** Entity management (adding/removing Rooms, Types, and Guests).
    *   **Accountant:** Financial statistics, occupancy rates, and analytics.
*   **Robust Validation:** Domain models enforce strict business rules (e.g., case-insensitive uniqueness, date overlap checking, regex-based email validation).

## 🛠️ Technologies & Concepts
*   **Language:** Java 21+ (utilizes modern features like Unnamed Variables).
*   **Core Concepts:** OOP, Collections Framework, Stream API, File I/O (Serialization), Exception Handling.
*   **Testing:** JUnit 5 (Unit testing for core business logic).

## 💻 How to Run

1. Compile the source code using your preferred IDE (IntelliJ IDEA / Eclipse) or via CLI.
2. Run the `hotel.app.HotelApplCLI` main class.
3. Choose your startup mode:
    - `1` to load existing `.data` files.
    - `2` to generate a random dataset for testing.
    - `3` to start fresh.
4. Navigate the interactive menus using integer inputs.

## 📁 Architecture Highlight
The application strictly separates UI (`cli` package) from Business Logic (`service` and `model` packages). The `HotelApplContext` acts as a central dependency injection container, ensuring services are decoupled and easily testable.