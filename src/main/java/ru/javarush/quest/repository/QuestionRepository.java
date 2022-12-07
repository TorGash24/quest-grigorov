package ru.javarush.quest.repository;

import lombok.Getter;
import lombok.NonNull;
import ru.javarush.quest.model.Quest;

import java.util.HashMap;

@Getter
@NonNull
public class QuestionRepository {
    private final HashMap<String, Quest> quests = new HashMap<>();
}
