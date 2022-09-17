package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Locale;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository<User>{

    public UserRepository() {
        map.put(1L, User.with()
                .id(1L).login("user").password("qwerty")
                .avatar("no_image").role(Role.USER).locale(Locale.EN).build());
        map.put(2L, User.with()
                .id(2L).login("quest").password("lol")
                .avatar("no_image").role(Role.GUEST).locale(Locale.EN).build());
        map.put(3L, User.with()
                .id(3L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).locale(Locale.RU).build());
    }

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

    //Temp


}
