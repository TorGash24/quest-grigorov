package ru.javarush.quest.repository;

import lombok.Getter;
import lombok.NonNull;
import ru.javarush.quest.model.User;

import java.util.HashMap;
import java.util.Map;

@Getter
@NonNull
public class UserRepository {
    private final Map<String, User> repository = new HashMap<>();
}
