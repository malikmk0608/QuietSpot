# QuietSpot - Campus Noise Tracker

QuietSpot is a Java console application that enables students and campus members to track, record, and analyze ambient noise levels across campus locations to discover the best quiet study spots and hours.

---

## Features

- **Add Observation**: Record noise levels (`1-Quiet`, `2-Moderate`, `3-Noisy`) for specific locations, dates, and times.
- **View Observations**: Browse all recorded noise data.
- **Update Observation**: Modify previously logged observations.
- **Delete Observation**: Remove an observation entry by ID.
- **Location Noise Summary**: View aggregated average noise levels and noise categories across locations.
- **Find Quiet Study Hours**: Identify quieter hours of the day for any selected campus spot based on recorded observations.

---

## Prerequisites

- **Java JDK 8 or higher**
- **MySQL Database Server**
- **MySQL Connector/J** (bundled under `lib/mysql-connector-j-26.10.0.jar`)

---

## Database Setup

1. Open your MySQL client and create the database:
   ```sql
   CREATE DATABASE quietspot;
   USE quietspot;
   ```

2. Create the `observations` table:
   ```sql
   CREATE TABLE observations (
       id INT AUTO_INCREMENT PRIMARY KEY,
       location VARCHAR(100) NOT NULL,
       observation_date DATE NOT NULL,
       observation_time TIME NOT NULL,
       noise_level INT NOT NULL CHECK (noise_level BETWEEN 1 AND 3)
   );
   ```

3. Configure your database credentials:
   - Copy `db.properties.example` to `db.properties` and add your password:
     ```properties
     db.url=jdbc:mysql://localhost:3306/quietspot
     db.user=root
     db.password=your_mysql_password
     ```
   - *(Note: `db.properties` is listed in `.gitignore`, so your password is never committed to GitHub).*
   - Alternatively, you can use environment variables (`DB_USER`, `DB_PASSWORD`).

---

## How to Compile & Run

From the project root directory:

1. **Compile**:
   ```bash
   javac -cp ".:lib/*" *.java
   ```

2. **Run**:
   ```bash
   java -cp ".:lib/*" Main
   ```

*(On Windows, use a semicolon instead of colon: `javac -cp ".;lib/*" *.java` and `java -cp ".;lib/*" Main`)*
