<%@ page contentType="text/html;charset=UTF-8" language="java" import="model.GameRound" %>
<%
    GameRound r = (GameRound) session.getAttribute("round");
    if (r == null) { response.sendRedirect(request.getContextPath() + "/index.jsp"); return; }
    String name = String.valueOf(session.getAttribute("playerName"))
            .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Mila - Math Whiz | Game</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="card">
    <h1>Mila &ndash; Math Whiz</h1>
    <p class="sub">Good luck, <b><%= name %></b>! &nbsp;|&nbsp; Difficulty: NORMAL</p>
    <form action="${pageContext.request.contextPath}/game" method="post">
        <input type="hidden" name="action" value="submit">

        <div class="q"><span class="marks">2 marks</span>
            <b>Q1.</b> Is <b><%= r.getPrimeNumber() %></b> a prime number? (enter 1 for Prime, 0 for Not Prime)
            <input type="number" name="a1" min="0" max="1" step="1" required style="margin-top:10px;margin-bottom:0">
        </div>

        <div class="q"><span class="marks">2 marks</span>
            <b>Q2.</b> What is <b><%= r.getModA() %> % <%= r.getModB() %></b> (remainder)?
            <input type="number" name="a2" step="1" required style="margin-top:10px;margin-bottom:0">
        </div>

        <div class="q"><span class="marks">3 marks</span>
            <b>Q3.</b> Find <b><%= r.getTrig1Type() %>(<%= (int) r.getTrig1Angle() %>&deg;)</b> (round to 2 decimals)
            <input type="number" name="a3" step="any" required style="margin-top:10px;margin-bottom:0">
        </div>

        <div class="q"><span class="marks">3 marks</span>
            <b>Q4.</b> Find <b><%= r.getTrig2Type() %>(<%= (int) r.getTrig2Angle() %>&deg;)</b> (round to 2 decimals)
            <input type="number" name="a4" step="any" required style="margin-top:10px;margin-bottom:0">
        </div>

        <button class="btn" type="submit">Submit Answers</button>
    </form>
</div>
</body>
</html>
