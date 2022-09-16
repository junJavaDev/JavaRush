package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository<User>{

    @Override
    public Collection<User> find(User pattern) {
        return map.values().stream()
                .filter(user -> isOk(pattern, user, User::getId)
                        && isOk(pattern, user, User::getAvatar)
                        && isOk(pattern, user, User::getLogin)
                        && isOk(pattern, user, User::getPassword)
                        && isOk(pattern, user, User::getRole))
                .toList();
    }

}
