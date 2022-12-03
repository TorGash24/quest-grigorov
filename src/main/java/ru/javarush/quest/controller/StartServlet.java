package ru.javarush.quest.controller;

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

@WebServlet(name="startServlet", value="/start")
public class StartServlet extends HttpServlet {
    private UserRepository userRepository;
    //private QuestionRepository questionRepository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userRepository = (UserRepository) servletContext.getAttribute("userRepository");
        //questionRepository = (QuestionRepository) servletContext.getAttribute("questionRepository");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");

        HttpSession session = req.getSession();

        if (session.getAttribute("userName") != null) {
            resp.sendRedirect("quest");
            return;
        }

        User user;
        if (userRepository.isExist(userName)) {
            user = userRepository.getUserByName(userName);
        } else {
            user = new User(userName, req.getRemoteAddr());
            userRepository.save(user);
        }

        session.setAttribute("user", user);

        resp.sendRedirect("quest");
    }
}
