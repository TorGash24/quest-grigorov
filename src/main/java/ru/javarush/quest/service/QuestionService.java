package ru.javarush.quest.service;

import ru.javarush.quest.model.Answer;
import ru.javarush.quest.repository.QuestionRepository;

import java.util.List;

public class QuestionService {
    private final QuestionRepository questionRepository;

    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public String getQuestionTextById(String questName, long idQuestion) {
        return questionRepository.getQuests().get(questName).getTextQuestionById(idQuestion).get();
    }

    public List<Answer> getAnswersByQuestionId(String quest, int currentIdQuestion) {
        return questionRepository.getQuests().get(quest).getAnswersByIdQuestion(currentIdQuestion).get();
    }
}
