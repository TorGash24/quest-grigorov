package ru.javarush.quest.model;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Builder
public class User implements Serializable {
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
