package ru.javarush.quest.controller;

import lombok.extern.slf4j.Slf4j;
import ru.javarush.quest.model.User;
import ru.javarush.quest.repository.UserRepository;
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
import java.net.InetAddress;

@Slf4j
@WebServlet(name="startServlet", value="/start")
public class StartServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        userService = (UserService) servletContext.getAttribute("userService");
    }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");

        HttpSession session = req.getSession();

        if (session.getAttribute("userName") != null) {
            log.info("Session from user is open");
            resp.sendRedirect("quest");
            return;
        }

        User user = userService.createAndSaveUserFromRepository(userName);
        log.debug("Create user : {}", user);
        session.setAttribute("user", user);

        resp.sendRedirect("quest");
    }
}
