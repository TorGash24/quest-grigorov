package ru.javarush.quest.controller;

import com.fasterxml.jackson.databind.json.JsonMapper;
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
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        UserRepository userRepository = new UserRepository();
        servletContext.setAttribute("userRepository", userRepository);

        QuestionRepository questionRepository = new QuestionRepository();

        Quest quest;
        try {
            quest = loadJsonFile("quest.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        questionRepository.getQuests().put("MyQuest",quest);
        servletContext.setAttribute("questionRepository", questionRepository);
    }

    private Quest loadJsonFile(String fileName) throws IOException {
        File file = new File(Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getFile());
        return new JsonMapper().readValue(file, Quest.class);
    }
}
