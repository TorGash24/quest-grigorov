package ru.javarush.quest.repository;

import lombok.Getter;
import ru.javarush.quest.model.Answer;
import ru.javarush.quest.model.Quest;

import java.util.HashMap;
import java.util.List;

@Getter
public class QuestionRepository {

    private final HashMap<String, Quest> quests = new HashMap<>();


    public String getQuestionTextById(String questName, long idQuestion) {
        return quests.get(questName).getTextQuestionById(idQuestion);
    }

    public List<Answer> getAnswersByQuestionId(String quest, int currentIdQuestion) {
        return quests.get(quest).getAnswersByIdQuestion(currentIdQuestion);
    }
}
