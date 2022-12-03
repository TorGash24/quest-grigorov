package ru.javarush.quest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Quest {
    private String name;
    private List<Question> questions;

    public String getTextQuestionById(long idQuestion) {
        return questions.stream()
                .filter(q -> q.getId() == idQuestion)
                .findFirst()
                .get()
                .getText();
    }

    public List<Answer> getAnswersByIdQuestion(long idQuestion) {
        return questions.stream()
                .filter(q -> q.getId() == idQuestion)
                .findFirst()
                .get().getAnswers();

    }


}
