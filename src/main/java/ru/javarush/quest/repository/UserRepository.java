package ru.javarush.quest.repository;

import lombok.Getter;
import ru.javarush.quest.model.User;

import java.util.HashMap;
import java.util.Map;

@Getter
public class UserRepository {
    private final Map<String, User> repository = new HashMap<>();

    public User getUserByName(String userName) {
        return repository.get(userName);
    }

    public boolean isExist(String userName) {
        return repository.containsKey(userName);
    }

    public void save(User user) {
        repository.put(user.getName(), user);
    }

}
