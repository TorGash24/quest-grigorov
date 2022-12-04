package ru.javarush.quest.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

import ru.javarush.quest.model.Quest;
import ru.javarush.quest.repository.QuestionRepository;
import ru.javarush.quest.repository.UserRepository;
import ru.javarush.quest.service.QuestionService;
import ru.javarush.quest.service.UserService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@WebListener
public class ContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Starting application");
        ServletContext servletContext = sce.getServletContext();

        servletContext.setRequestCharacterEncoding("UTF-8");

        log.info("created user repository");
        UserRepository userRepository = new UserRepository();
        servletContext.setAttribute("userRepository", userRepository);

        log.info("created quest repository");
        QuestionRepository questionRepository = new QuestionRepository();

        Quest quest;
        try {
            log.info("load json file from quest");
            quest = loadJsonFile("quest.json");
        } catch (IOException e) {
            log.error("{}", e);
            throw new RuntimeException(e);
        }

        questionRepository.getQuests().put("MyQuest",quest);
        log.info("Save quest from quest repository");
        servletContext.setAttribute("questionRepository", questionRepository);

        UserService userService = new UserService(userRepository);
        log.info("Create userService");
        servletContext.setAttribute("userService", userService);

        QuestionService questionService = new QuestionService(questionRepository);
        log.info("Create questionService");
        servletContext.setAttribute("questionService", questionService);

    }

    private Quest loadJsonFile(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        log.info("access load file : {}", fileName);
        return new JsonMapper().readValue(file, Quest.class);
    }
}
