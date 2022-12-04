package ru.javarush.quest.model;

import lombok.*;

@Data
@Getter
@Builder
public class User {
    private final String name;
    private int countGames;
    private int countWin;
    private int currentIdQuestion;


    public void incrCountGames() {
        countGames++;
    }

    public void incrWin() {
        countWin++;
    }
}
