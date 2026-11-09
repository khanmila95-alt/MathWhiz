<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mila - Math Whiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h1>Mila &ndash; Math Whiz</h1>
    <p class="sub">Four quick questions: prime, modulus and trigonometry. Difficulty: NORMAL</p>
    <% if (request.getAttribute("error") != null) { %>
        <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>
    <form action="${pageContext.request.contextPath}/game" method="post">
        <input type="hidden" name="action" value="start">
        <label for="playerName">Player Name</label>
        <input type="text" id="playerName" name="playerName" maxlength="100" placeholder="Enter your name" required>
        <div class="actions">
            <button class="btn" type="submit">Start Game</button>
            <a class="btn alt" href="${pageContext.request.contextPath}/leaderboard">Leaderboard</a>
        </div>
    </form>
</div>
</body>
</html>
