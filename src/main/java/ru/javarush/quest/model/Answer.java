package ru.javarush.quest.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {
    private long id;
    private String text;
    private Integer nextQuestionId;
}
