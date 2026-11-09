<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.List,model.GameScore" %>
<%
    @SuppressWarnings("unchecked")
    List<GameScore> scores = (List<GameScore>) request.getAttribute("scores");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mila - Math Whiz | Leaderboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h1>Top 10 Leaderboard</h1>
    <% if (request.getAttribute("dbError") != null) { %>
        <div class="error"><%= request.getAttribute("dbError") %></div>
    <% } %>
    <table>
        <tr><th>#</th><th>Player</th><th>Score</th><th>Difficulty</th><th>Date / Time</th></tr>
        <% if (scores == null || scores.isEmpty()) { %>
            <tr><td colspan="5">No scores yet. Be the first!</td></tr>
        <% } else { int rank = 1; for (GameScore g : scores) { %>
            <tr>
                <td><%= rank++ %></td>
                <td><%= g.getSafeName() %></td>
                <td><%= g.getScore() %></td>
                <td><%= g.getDifficulty() %></td>
                <td><%= g.getPlayedAt() %></td>
            </tr>
        <% } } %>
    </table>
    <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/index.jsp">Play Game</a>
    </div>
</div>
</body>
</html>
