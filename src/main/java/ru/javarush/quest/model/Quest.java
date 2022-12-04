package ru.javarush.quest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class Quest {
    private String name;
    private List<Question> questions;

    public Optional<String> getTextQuestionById(long idQuestion) {
        return questions.stream()
                .filter(q -> q.getId() == idQuestion)
                .findFirst()
                .map(Question::getText)
                .stream()
                .findFirst();
    }

    public Optional<List<Answer>> getAnswersByIdQuestion(long idQuestion) {
        return questions.stream()
                .filter(q -> q.getId() == idQuestion)
                .findFirst()
                .map(Question::getAnswers);
    }
}
