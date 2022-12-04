package ru.javarush.quest.controller;

import lombok.extern.slf4j.Slf4j;
import ru.javarush.quest.model.Answer;
import ru.javarush.quest.model.User;
import ru.javarush.quest.repository.QuestionRepository;
import ru.javarush.quest.repository.UserRepository;
import ru.javarush.quest.service.QuestionService;
import ru.javarush.quest.service.UserService;

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

@Slf4j
@WebServlet(name = "QuestServlet", value = "/quest")
public class QuestServlet extends HttpServlet {
    QuestionService questionService;
    private static final String QUEST_NAME = "MyQuest";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext servletContext = config.getServletContext();
        questionService = (QuestionService) servletContext.getAttribute("questionService");
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        int currentIdQuestion = user.getCurrentIdQuestion();
        String questionText = questionService.getQuestionTextById(QUEST_NAME, currentIdQuestion);
        List<Answer> answers = questionService.getAnswersByQuestionId(QUEST_NAME, currentIdQuestion);

        log.debug("User {}, currentIdQuestion: {}, questionText : {}, List answers : {}",
                user.getName(), user.getCurrentIdQuestion(), questionText, answers);

        if (currentIdQuestion == 3) {
            req.setAttribute("text", questionText);
            user.incrWin();
            user.incrCountGames();
            log.debug("WINING: User {}, currentIdQuestion: {}, questionText : {}, List answers : {}",
                    user.getName(), user.getCurrentIdQuestion(), questionText, answers);
            req.getRequestDispatcher("/WEB-INF/pages/final.jsp").forward(req, resp);
            return;
        }

        if (answers.isEmpty()) {
            req.setAttribute("text", questionText);
            user.incrCountGames();
            log.debug("LOSS: User {}, currentIdQuestion: {}, questionText : {}, List answers : {}",
                    user.getName(), user.getCurrentIdQuestion(), questionText, answers);
            req.getRequestDispatcher("/WEB-INF/pages/final.jsp").forward(req, resp);
            return;
        }

        req.setAttribute("answers", answers);
        req.setAttribute("userName", user.getName());
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
        log.debug("CurrentQuestionId: {}", currentQuestionId);
        user.setCurrentIdQuestion(currentQuestionId);

        resp.sendRedirect("quest");
    }
}
