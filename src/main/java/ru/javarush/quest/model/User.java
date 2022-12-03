package ru.javarush.quest.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@EqualsAndHashCode
public class User {
    private final String name;
    private int countGames;
    private int countWin;
    private int currentIdQuestion;

    private String ipAddress;

    public User(String name, String ipAddress) {
        this.name = name;
        this.countGames = 0;
        this.countWin = 0;
        this.currentIdQuestion = 0;
        this.ipAddress = ipAddress;
    }

    public void incrCountGames() {
        countGames++;
    }

    public void incrWin() {
        countWin++;
    }
}
