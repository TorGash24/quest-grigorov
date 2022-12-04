package ru.javarush.quest.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ru.javarush.quest.model.Quest;
import ru.javarush.quest.repository.QuestionRepository;
import ru.javarush.quest.repository.UserRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

@WebListener
public class ContextListener implements ServletContextListener {
    private static final Logger LOGGER = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Starting application");
        ServletContext servletContext = sce.getServletContext();

        LOGGER.info("created user repository");
        UserRepository userRepository = new UserRepository();
        servletContext.setAttribute("userRepository", userRepository);

        LOGGER.info("created quest repository");
        QuestionRepository questionRepository = new QuestionRepository();

        Quest quest;
        try {
            LOGGER.info("load json file from quest");
            quest = loadJsonFile("quest.json");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        questionRepository.getQuests().put("MyQuest",quest);
        LOGGER.info("Save quest from quest repository");
        servletContext.setAttribute("questionRepository", questionRepository);
    }

    private Quest loadJsonFile(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        LOGGER.info("access load file : {}", fileName);
        return new JsonMapper().readValue(file, Quest.class);
    }
}
