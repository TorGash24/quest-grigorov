package ru.javarush.quest.service;

import ru.javarush.quest.model.User;
import ru.javarush.quest.repository.UserRepository;

public class UserService {
    private  final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createAndSaveUserFromRepository(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("UserName == null");
        }

        if (isExist(userName)) {
            return getUserByName(userName);
        } else {
            User user = createUserByName(userName);
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

    private User createUserByName(String userName) {
        return User.builder()
                .name(userName)
                .countGames(0)
                .countWin(0)
                .currentIdQuestion(0)
                .build();
    }
}
