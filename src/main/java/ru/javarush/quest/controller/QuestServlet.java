package ru.javarush.quest.controller;

import ru.javarush.quest.model.Answer;
import ru.javarush.quest.model.User;
import ru.javarush.quest.repository.QuestionRepository;
import ru.javarush.quest.repository.UserRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import java.util.List;

@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    UserRepository userRepository;
    QuestionRepository questionRepository;
    private static final String QUEST_NAME = "MyQuest";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = config.getServletContext();
        userRepository= (UserRepository) servletContext.getAttribute("userRepository");
        questionRepository = (QuestionRepository) servletContext.getAttribute("questionRepository");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int currentIdQuestion = user.getCurrentIdQuestion();
        String questionText = questionRepository.getQuestionTextById(QUEST_NAME, currentIdQuestion);
        List<Answer> answers = questionRepository.getAnswersByQuestionId(QUEST_NAME, currentIdQuestion);

        if (answers.isEmpty() && !questionText.equals("Тебя вернули домой. Победа!")) {
            req.setAttribute("text", questionText);
            user.incrCountGames();
            req.getRequestDispatcher("/WEB-INF/pages/final.jsp").forward(req, resp);
        }

        if (questionText.equals("Тебя вернули домой. Победа!")) {
            req.setAttribute("text", questionText);
            user.incrWin();
            user.incrCountGames();
            req.getRequestDispatcher("/WEB-INF/pages/final.jsp").forward(req, resp);
        }

        req.setAttribute("answers", answers);
        req.setAttribute("userName", user.getName());
        req.setAttribute("ipAddress", user.getIpAddress());
        req.setAttribute("countGames", user.getCountGames());
        req.setAttribute("countWin", user.getCountWin());
        req.setAttribute("questionText", questionText);

        req.getRequestDispatcher("/WEB-INF/pages/quest.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int currentQuestionId = Integer.parseInt(req.getParameter("nextQuestionId"));
        user.setCurrentIdQuestion(currentQuestionId);

        resp.sendRedirect("quest");
    }
}
