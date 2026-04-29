# Hotel Data System 🏨

![Status: In Progress](https://img.shields.io/badge/Status-In%20Progress-yellow)

A console-based Enterprise-like application for hotel booking management.

## 🚧 Project Status
**The project is under active development.** 
Currently working on implementing a full-fledged interactive Command Line Interface (CLI) for convenient feature management. 
At this stage, a test version of the application (`HotelAppl`) is available, which demonstrates the core functionality: it automatically generates a mock hotel database, performs analytics calculations, and successfully saves/restores the system state using local binary files (`.data`).

---

The project is developed to demonstrate solid knowledge of Core Java, Object-Oriented Programming (OOP) principles, and building a clean, scalable architecture without using external frameworks.

## 🛠 Technologies & Architecture

*   **Language:** Java (Core)
*   **Architecture Pattern:** Layered Architecture (Model, Service, CLI Interface)
*   **Design Patterns:** Command / Item (for building isolated console menu items)
*   **Data Persistence:** Custom database implementation based on Java Serialization (saving the object graph state)
*   **Validation:** Fail-Fast approach using built-in mechanisms (`Objects.requireNonNull`) and a strict exception hierarchy (`IllegalArgumentException`, `IllegalStateException`).

## 🚀 Key Features

The application is divided into logical management blocks:

1.  **Room Fund Management:**
    *   Create and remove room types (RoomTypes) with pricing and capacity configuration.
    *   Register specific physical rooms (Rooms).
2.  **Guest Management:**
    *   Register new guests with email format and age validation.
    *   Protection against deleting guests with active or past bookings.
3.  **Booking System:**
    *   Search for available rooms for specific dates.
    *   Overbooking protection (preventing date overlaps).
    *   Automatic calculation of nights and total stay price.
4.  **Analytics & Reporting:**
    *   Calculation of total income and average booking price.
    *   Determination of the most popular room types (including filtering by guest age groups).
5.  **Persistence (State Saving):**
    *   Automatic data saving on application exit.
    *   Database restoration on restart.
    *   *Note: On the first launch (if data files are missing), the built-in generator will automatically create a mock dataset.*

## ⚙️ How to Run (Test Version)

The project requires no external dependencies or DBMS installation.

1.  Clone the repository to your local machine:
    ```bash
    git clone [https://github.com/YOUR_USERNAME/hotel-data-system.git](https://github.com/YOUR_USERNAME/hotel-data-system.git)
    ```
2.  Open the project in any modern IDE (IntelliJ IDEA, Eclipse).
3.  Run the main application class:
    ```text
    src/hotel/app/HotelAppl.java
    ```
4.  If there are no saved files, the system will generate mock data and print an analytical report to the console.

## 📁 Project Structure

*   `hotel.model` — POJO domain classes (Hotel, Guest, Room, Booking).
*   `hotel.interfaces` — Service contracts.
*   `hotel.service` — Business logic and file system operations implementation.
*   `hotel.items` — Controllers for the upcoming console interface (extending abstract `HotelItem`).
*   `hotel.generator` — Utility for database seeding with random values.
