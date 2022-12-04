package ru.javarush.quest.model;

import lombok.*;

@Getter
@ToString
public class Answer {
    private long id;
    private String text;
    private Integer nextQuestionId;
}
