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
   - By default, `DatabaseConnection.java` connects to `jdbc:mysql://localhost:3306/quietspot`.
   - Set the environment variables `DB_USER` and `DB_PASSWORD` or edit `DatabaseConnection.java` with your MySQL credentials:
     ```bash
     export DB_USER="root"
     export DB_PASSWORD="your_mysql_password"
     ```

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
