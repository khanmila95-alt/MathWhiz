package controller;

import dao.ScoreDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.GameRound;
import model.GameScore;
import service.MathService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/game", "/leaderboard"})
public class GameServlet extends HttpServlet {

    private static final String DIFFICULTY = "NORMAL";
    private final ScoreDAO dao = new ScoreDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getServletPath().equals("/leaderboard")) {
            showLeaderboard(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if ("start".equals(action)) {
            startGame(req, resp);
        } else if ("submit".equals(action)) {
            submitAnswers(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }

    private void startGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playerName");
        name = name == null ? "" : name.trim();
        if (name.isEmpty() || name.length() > 100) {
            req.setAttribute("error", "Please enter a player name (1-100 characters).");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            return;
        }
        HttpSession session = req.getSession(true);
        session.setAttribute("playerName", name);
        session.setAttribute("round", new GameRound());
        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    private void submitAnswers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        GameRound r = session == null ? null : (GameRound) session.getAttribute("round");
        String name = session == null ? null : (String) session.getAttribute("playerName");
        if (r == null || name == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        // ---- calculate correct answers with the Java methods ----
        int expPrime = MathService.isPrime(r.getPrimeNumber());
        int expMod = MathService.calculateMod(r.getModA(), r.getModB());
        double expT1 = MathService.calculateTrig(r.getTrig1Type(), r.getTrig1Angle());
        double expT2 = MathService.calculateTrig(r.getTrig2Type(), r.getTrig2Angle());

        // ---- evaluate player answers (invalid input = wrong) ----
        boolean primeCorrect = Integer.valueOf(expPrime).equals(parseInt(req.getParameter("a1")));
        boolean modCorrect = Integer.valueOf(expMod).equals(parseInt(req.getParameter("a2")));
        boolean trig1Correct = closeTo(expT1, req.getParameter("a3"));
        boolean trig2Correct = closeTo(expT2, req.getParameter("a4"));

        int totalScore = 0;
        if (primeCorrect) totalScore += 2;
        if (modCorrect)   totalScore += 2;
        if (trig1Correct) totalScore += 3;
        if (trig2Correct) totalScore += 3;

        req.setAttribute("score", totalScore);
        req.setAttribute("expPrime", expPrime);
        req.setAttribute("expMod", expMod);
        req.setAttribute("expT1", String.format("%.4f", expT1));
        req.setAttribute("expT2", String.format("%.4f", expT2));
        req.setAttribute("primeCorrect", primeCorrect);
        req.setAttribute("modCorrect", modCorrect);
        req.setAttribute("trig1Correct", trig1Correct);
        req.setAttribute("trig2Correct", trig2Correct);

        try {
            dao.saveScore(new GameScore(name, totalScore, DIFFICULTY));
        } catch (SQLException e) {
            getServletContext().log("Could not save score", e);
            req.setAttribute("dbError", "Score could not be saved: " + e.getMessage());
        }
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }

    private void showLeaderboard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("scores", dao.getTop10());
        } catch (SQLException e) {
            getServletContext().log("Could not load leaderboard", e);
            req.setAttribute("dbError", "Could not load leaderboard: " + e.getMessage());
        }
        req.getRequestDispatcher("/leaderboard.jsp").forward(req, resp);
    }

    /** Returns the parsed int, or null for invalid input. */
    private Integer parseInt(String s) {
        try { return Integer.valueOf(s.trim()); } catch (Exception e) { return null; }
    }

    private boolean closeTo(double expected, String s) {
        try { return MathService.isClose(expected, Double.parseDouble(s.trim())); }
        catch (Exception e) { return false; }
    }
}
