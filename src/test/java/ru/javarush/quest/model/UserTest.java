package ru.javarush.quest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User user = new User("John", "");

    @Test
    void checkIncrCountGames() {
        user.incrCountGames();
        assertEquals(1, user.getCountGames());
    }

    @Test
    void checkIncrWin() {
        user.incrWin();
        assertEquals(1, user.getCountWin());
    }

    @Test
    void checkToString() {
        User newUser = new User("Masha", "");
        assertEquals("User(id=null, name=Masha, countGames=0, countWin=0)", newUser.toString());
    }

}