# Mila – Math Whiz

A web-based mathematical game built with Java Servlets, JSP and JDBC (MySQL). The player enters a name, answers four questions (prime, modulus, two trigonometry), sees the final score out of 10 and can view the Top 10 leaderboard.

## Objectives
- Write reusable Java methods for prime, modulus and trigonometric calculations
- Process requests and responses with Servlets
- Store and retrieve scores with JDBC and `PreparedStatement`
- Show a sorted Top 10 leaderboard

## Technologies Used
Java 17, Jakarta Servlet 6 / JSP, JDBC, MySQL 8, Apache Tomcat 10.1, Maven, HTML/CSS

## Software Requirements
- JDK 17+
- Apache Tomcat 10.1
- MySQL 8.x
- IntelliJ IDEA (Smart Tomcat plugin) or any IDE with Tomcat support
- Maven

## Database Configuration
1. Run `database/math_whiz_db.sql` in MySQL.
2. Open `src/main/java/dao/ScoreDAO.java` and set `URL`, `USER`, `PASSWORD`.

## Database Creation SQL
```sql
CREATE DATABASE IF NOT EXISTS math_whiz_db;
USE math_whiz_db;

CREATE TABLE IF NOT EXISTS game_scores (
    id          INT PRIMARY KEY AUTO_INCREMENT,
    player_name VARCHAR(100) NOT NULL,
    score       INT NOT NULL,
    difficulty  VARCHAR(20) NOT NULL DEFAULT 'NORMAL',
    played_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Steps to Run
1. Clone the repository and open the `MathWhiz` folder in IntelliJ as a Maven project.
2. Create the database (see above) and update the credentials in `ScoreDAO.java`.
3. Add a Smart Tomcat run configuration: Tomcat 10.1, context path `/MathWhiz`.
4. Run it and open `http://localhost:8080/MathWhiz/`.

To test the math methods only, run `MathService.main()`.

## Game Rules & Scoring
| Question | Area | Marks |
|---|---|---|
| Q1 | Prime (1 = prime, 0 = not prime) | 2 |
| Q2 | Modulus (integers, divisor never 0) | 2 |
| Q3 | Trigonometry (sin/cos/tan, degrees) | 3 |
| Q4 | Trigonometry (sin/cos/tan, degrees) | 3 |
| **Total** | | **10** |

- Trig answers are accepted within a tolerance of 0.01.
- Difficulty is `NORMAL` and is stored with every score.
- Leaderboard: `ORDER BY score DESC, played_at ASC LIMIT 10`.

## Application Screenshots

### Welcome Page
![Welcome Page](screenshots/home.png)

### Game Page
![Game Page](screenshots/game.png)

### Result Page
![Result Page](screenshots/result.png)

### Leaderboard
![Leaderboard](screenshots/leaderboard.png)

## GitHub Project Directory
https://github.com/username/repository/tree/main/MathWhiz
