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
        ServletContext servletContext = sce.getServletContext();

        servletContext.setRequestCharacterEncoding("UTF-8");

        UserRepository userRepository = new UserRepository();
        log.debug("created user repository, size : {}", userRepository.getRepository().size());
        servletContext.setAttribute("userRepository", userRepository);

        QuestionRepository questionRepository = new QuestionRepository();
        log.info("created quest repository, size : {}", questionRepository.getQuests().size());

        String fileName = "quest.json";
        Quest quest;
        try {
            quest = loadJsonFile(fileName);
            log.debug("file read");
        } catch (IOException e) {
            log.error("Error load file : {e}", e);
            throw new RuntimeException(e);
        }

        questionRepository.getQuests().put("MyQuest",quest);
        servletContext.setAttribute("questionRepository", questionRepository);
        log.debug("Save quest from questRepository");

        UserService userService = new UserService(userRepository);
        log.debug("Create userService");
        servletContext.setAttribute("userService", userService);

        QuestionService questionService = new QuestionService(questionRepository);
        log.debug("Create questionService");
        servletContext.setAttribute("questionService", questionService);

    }

    private Quest loadJsonFile(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        log.info("file loaded : {}", fileName);
        return new JsonMapper().readValue(file, Quest.class);
    }
}
