package ru.javarush.quest.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Question {
    private long id;
    private String text;
    private List<Answer> answers;
}
