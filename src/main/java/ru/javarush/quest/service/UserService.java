package ru.javarush.quest.service;

import ru.javarush.quest.model.User;
import ru.javarush.quest.repository.UserRepository;

public class UserService {
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createAndSaveUserFromRepository(String userName) {
        if (isExist(userName)) {
            return getUserByName(userName);
        } else {
            User user = User.builder()
                    .name(userName)
                    .countGames(0)
                    .countWin(0)
                    .currentIdQuestion(0)
                    .build();

            save(user);

            return user;
        }
    }

    public boolean isExist(String userName) {
        return userRepository.getRepository().containsKey(userName);
    }

    public void save(User user) {
        userRepository.getRepository().put(user.getName(), user);
    }

    public User getUserByName(String userName) {
        return userRepository.getRepository().get(userName);
    }

    public void incrWin(User user) {
        userRepository.getRepository().get(user.getName()).incrWin();
    }
}
