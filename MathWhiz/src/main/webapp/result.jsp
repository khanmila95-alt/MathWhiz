<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object score = request.getAttribute("score");
    if (score == null) { response.sendRedirect(request.getContextPath() + "/index.jsp"); return; }
    String name = String.valueOf(session.getAttribute("playerName"))
            .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
    boolean p = (Boolean) request.getAttribute("primeCorrect");
    boolean m = (Boolean) request.getAttribute("modCorrect");
    boolean t1 = (Boolean) request.getAttribute("trig1Correct");
    boolean t2 = (Boolean) request.getAttribute("trig2Correct");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mila - Math Whiz | Result</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h1>Result</h1>
    <p class="sub">Player: <b><%= name %></b> &nbsp;|&nbsp; Difficulty: NORMAL</p>
    <div class="score"><%= score %> / 10</div>

    <% if (request.getAttribute("dbError") != null) { %>
        <div class="error"><%= request.getAttribute("dbError") %></div>
    <% } %>

    <table>
        <tr><th>Question</th><th>Correct Answer</th><th>Result</th></tr>
        <tr><td>Q1 Prime (2)</td><td><%= request.getAttribute("expPrime") %></td><td class="<%= p ? "ok" : "bad" %>"><%= p ? "Correct" : "Wrong" %></td></tr>
        <tr><td>Q2 Modulus (2)</td><td><%= request.getAttribute("expMod") %></td><td class="<%= m ? "ok" : "bad" %>"><%= m ? "Correct" : "Wrong" %></td></tr>
        <tr><td>Q3 Trig (3)</td><td><%= request.getAttribute("expT1") %></td><td class="<%= t1 ? "ok" : "bad" %>"><%= t1 ? "Correct" : "Wrong" %></td></tr>
        <tr><td>Q4 Trig (3)</td><td><%= request.getAttribute("expT2") %></td><td class="<%= t2 ? "ok" : "bad" %>"><%= t2 ? "Correct" : "Wrong" %></td></tr>
    </table>

    <div class="actions">
        <a class="btn" href="${pageContext.request.contextPath}/leaderboard">View Leaderboard</a>
        <a class="btn alt" href="${pageContext.request.contextPath}/index.jsp">Play Again</a>
    </div>
</div>
</body>
</html>
