<div align="center">

# 🧮 Mila – Math Whiz

**A web-based mathematical game built with Java Servlets, JSP and JDBC**


*Answer four math challenges, earn up to 10 marks and climb the Top 10 leaderboard!*

</div>

---

## 📑 Table of Contents

1. [Project Description](#-project-description)
2. [Objectives](#-objectives)
3. [Features](#-features)
4. [Technologies Used](#-technologies-used)
5. [Software Requirements](#-software-requirements)
6. [Project Structure](#-project-structure)
7. [Database Configuration](#-database-configuration)
8. [Database Creation SQL](#-database-creation-sql)
9. [Steps to Run the Application](#-steps-to-run-the-application)
10. [Game Rules & Scoring System](#-game-rules--scoring-system)
11. [Math Functions](#-math-functions)
12. [Testing & Validation](#-testing--validation)
13. [Application Screenshots](#-application-screenshots)
14. [Troubleshooting](#-troubleshooting)
15. [Author](#-author)
16. [GitHub Project Directory](#-github-project-directory)

---

## 📖 Project Description

**Mila – Math Whiz** is a server-side web game. The player enters a name, answers **four mathematical questions** (one prime number, one modulus, two trigonometry), submits the answers and instantly sees the final score out of **10**.

Every correct answer is **calculated by Java methods at submit time**; no answers are stored manually. Each result (player name, score, difficulty and date/time) is saved in a MySQL database through JDBC, and the **Top 10 scores** are shown on a leaderboard.

## 🎯 Objectives

- Develop reusable Java methods for mathematical calculations
- Apply conditional logic and algorithm integration
- Understand Servlet request and response processing
- Implement JDBC connectivity and CRUD operations
- Use `PreparedStatement` for secure database operations
- Retrieve, sort and display database records
- Document and submit a complete project through GitHub

## ✨ Features

| Feature | Description |
|---|---|
| 🎲 Random questions | A new set of four questions is generated for every game |
| ✅ Calculated answers | Correct answers come from Java methods, never from stored values |
| 🔢 Three math areas | Prime number, modulus and trigonometry (sin / cos / tan) |
| 📐 Degree input | Angles are given in degrees and converted to radians |
| 🎯 Tolerance check | Decimal answers are accepted within ±0.01 |
| 💾 Score saving | Name, score, difficulty and date/time stored in MySQL |
| 🏆 Leaderboard | Top 10 scores, ties broken by earlier date/time |
| 🔒 Safe output | Player names are HTML-escaped to prevent script injection |
| 📋 Detailed result | Result page shows the correct answer for every question |

## 🛠 Technologies Used

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Web | Jakarta Servlet 6.0, JSP |
| Server | Apache Tomcat 10.1 |
| Database | MySQL 8.x |
| Connectivity | JDBC (`mysql-connector-j` 8.4.0) |
| Build tool | Maven |
| Frontend | HTML5, CSS3 |
| IDE | IntelliJ IDEA with Smart Tomcat plugin |

## 💻 Software Requirements

- JDK 17 or higher
- Apache Tomcat **10.1** (Tomcat 9 will not work, because the project uses `jakarta.*` packages)
- MySQL Server 8.x
- Maven (bundled with IntelliJ IDEA)
- IntelliJ IDEA (or any IDE with Tomcat support)
- A web browser

## 📂 Project Structure

```
MathWhiz/
├── src/main/java/
│   ├── controller/
│   │   └── GameServlet.java        # handles start, submit and leaderboard requests
│   ├── model/
│   │   ├── GameRound.java          # the four questions of one game
│   │   └── GameScore.java          # score record (name, score, difficulty, time)
│   ├── service/
│   │   └── MathService.java        # isPrime, calculateMod, sin / cos / tan
│   └── dao/
│       └── ScoreDAO.java           # JDBC: INSERT and top-10 SELECT
├── src/main/webapp/
│   ├── index.jsp                   # Screen 1: welcome page
│   ├── game.jsp                    # Screen 2: game page
│   ├── result.jsp                  # Screen 3: result page
│   ├── leaderboard.jsp             # Screen 4: top 10 leaderboard
│   ├── css/style.css
│   └── WEB-INF/web.xml
├── database/
│   └── math_whiz_db.sql            # database creation script
├── screenshots/                    # application screenshots
├── pom.xml
└── README.md
```

## 🗄 Database Configuration

1. Start the MySQL service.
2. Run `database/math_whiz_db.sql` (see below).
3. Open `src/main/java/dao/ScoreDAO.java` and update your credentials:

```java
private static final String URL =
    "jdbc:mysql://localhost:3306/math_whiz_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

## 🧱 Database Creation SQL

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

**Table: `game_scores`**

| Field | Type | Notes |
|---|---|---|
| `id` | INT | Primary key, auto increment |
| `player_name` | VARCHAR(100) | Player's name |
| `score` | INT | Final score (0–10) |
| `difficulty` | VARCHAR(20) | Currently always `NORMAL` |
| `played_at` | TIMESTAMP | Date and time of play |

**Leaderboard query**

```sql
SELECT id, player_name, score, difficulty, played_at
FROM game_scores
ORDER BY score DESC, played_at ASC
LIMIT 10;
```

## ▶️ Steps to Run the Application

1. **Clone** the repository:
   ```bash
   git clone https://github.com/username/repository.git
   ```
2. Create the database using the SQL script above.
3. Update the MySQL password in `ScoreDAO.java`.
4. Open the **`MathWhiz`** folder (the one containing `pom.xml`) in IntelliJ IDEA and let Maven load the dependencies.
5. Create a **Smart Tomcat** run configuration:

   | Setting | Value |
   |---|---|
   | Tomcat server | Apache Tomcat 10.1 |
   | Deployment directory | `src/main/webapp` |
   | Context path | `/MathWhiz` |
   | Server port | `8080` |

6. Click **Run ▶** and open:
   ```
   http://localhost:8080/MathWhiz/
   ```

> 💡 To test only the math methods, run `MathService.main()`; results appear in the console.

## 🎮 Game Rules & Scoring System

**Difficulty: `NORMAL`** (stored in the database for every game)

| Question | Mathematical Area | Marks |
|---|---|---|
| Q1 | Prime number (enter `1` = prime, `0` = not prime) | 2 |
| Q2 | Modulus (integer operands only) | 2 |
| Q3 | Trigonometric value (sin / cos / tan) | 3 |
| Q4 | Trigonometric value (sin / cos / tan) | 3 |
| **Total** | | **10** |

**Rules**

- A number less than 2 is not prime.
- The divisor in the modulus question is never zero.
- Angles are in degrees and converted to radians with `Math.toRadians()`.
- Trigonometric answers are accepted within a tolerance of **0.01**.
- Empty or invalid answers earn no marks.

**Scoring logic**

```java
int totalScore = 0;

if (primeCorrect) totalScore += 2;
if (modCorrect)   totalScore += 2;
if (trig1Correct) totalScore += 3;
if (trig2Correct) totalScore += 3;
```

## 🧪 Math Functions

All functions are in `service/MathService.java`.

```java
public static int    isPrime(int number)          // 1 = prime, 0 = not prime
public static int    calculateMod(int a, int b)   // a % b, error if b == 0
public static double calculateSin(double degree)
public static double calculateCos(double degree)
public static double calculateTan(double degree) // error if undefined (90°, 270° ...)
```

## ✅ Testing & Validation

| Test Case | Expected Result | Status |
|---|---|---|
| Prime input: 7 | 1 | ✅ |
| Prime input: 8 | 0 | ✅ |
| Prime input: 1 | 0 | ✅ |
| Modulus: 17 % 5 | 2 | ✅ |
| Modulus: -17 % 5 | -2 | ✅ |
| Sine: 30° | 0.5 | ✅ |
| Cosine: 60° | 0.5 | ✅ |
| Tangent: 45° | 1.0 | ✅ |
| Divisor: 0 | Validation error | ✅ |
| Tangent: 90° | Validation error | ✅ |
| All answers correct | Maximum 10 marks | ✅ |
| Incorrect answers | Corresponding marks not awarded | ✅ |

## 🖼 Application Screenshots

### Welcome Page
![Welcome Page](screenshots/home.png)

### Game Page
![Game Page](screenshots/game.png)

### Result Page
![Result Page](screenshots/result.png)

### Leaderboard
![Leaderboard](screenshots/leaderboard.png)

### Database Table
![Database Table](screenshots/database.png)

### Math Function Test Output
![Math Function Test](screenshots/math_test.png)

## 🔧 Troubleshooting

| Problem | Solution |
|---|---|
| `ClassNotFoundException: jakarta.servlet...` | Use Tomcat **10.1**, not Tomcat 9 |
| "Score could not be saved" | Check the MySQL password in `ScoreDAO.java` and make sure the MySQL service is running |
| `Unknown database 'math_whiz_db'` | Run `database/math_whiz_db.sql` first |
| Port 8080 already in use | Change the server port in the Smart Tomcat configuration (e.g. `8081`) |
| `pom.xml` not detected | Open the inner `MathWhiz` folder that directly contains `pom.xml` |
| 404 error in browser | Check that the context path is `/MathWhiz` and the deployment directory is `src/main/webapp` |

## 👩‍💻 Author

**Meherun Nesa Mila**
ID: IT24002
Department of ICT
Mawlana Bhasani Science and Technology University (MBSTU)

## 🔗 GitHub Project Directory

```
https://github.com/username/repository/tree/main/MathWhiz
```

> Replace `username/repository` with your own GitHub username and repository name before submitting.

---

<div align="center">

Made with ☕ and Java for the *Java Servlet & JDBC* lab practice.

</div>


<img width="1917" height="1020" alt="Screenshot 2026-11-09 202315" src="https://github.com/user-attachments/assets/b5efb805-f095-427c-87fd-257eefbe7886" />
