package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository<User>{

    public UserRepository() {
        map.put(1L, User.with()
                .id(1L).login("user").password("qwerty")
                .avatar("no_image").role(Role.USER).language(Language.EN).build());
        map.put(2L, User.with()
                .id(2L).login("quest").password("lol")
                .avatar("no_image").role(Role.GUEST).language(Language.EN).build());
        map.put(3L, User.with()
                .id(3L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(4L, User.with()
                .id(4L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(5L, User.with()
                .id(5L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(6L, User.with()
                .id(6L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(7L, User.with()
                .id(7L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(8L, User.with()
                .id(8L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(9L, User.with()
                .id(9L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(10L, User.with()
                .id(10L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(11L, User.with()
                .id(11L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(12L, User.with()
                .id(12L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(13L, User.with()
                .id(13L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(14L, User.with()
                .id(14L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(15L, User.with()
                .id(15L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(16L, User.with()
                .id(16L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(17L, User.with()
                .id(17L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(18L, User.with()
                .id(18L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(19L, User.with()
                .id(19L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(20L, User.with()
                .id(20L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(33L, User.with()
                .id(33L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        map.put(55L, User.with()
                .id(55L).login("admin").password("admin")
                .avatar("no_image").role(Role.ADMIN).language(Language.RU).build());
        setLastId(new TreeSet<>(map.keySet()).last());
    }

    @Override
    public void update(User updUser) {
        long updId = updUser.getId();
        User existUser = map.get(updId);

        if (existUser != null) {
            updUser.setLanguage(existUser.getLanguage());
        }
        map.put(updId, updUser);
    }

//    @Override
//    public Collection<User> find(User pattern) {
//        return map.values().stream()
//                .filter(user -> isOk(pattern, user, User::getId)
//                        && isOk(pattern, user, User::getAvatar)
//                        && isOk(pattern, user, User::getLogin)
//                        && isOk(pattern, user, User::getPassword)
//                        && isOk(pattern, user, User::getRole))
//                .toList();
//    }

    @Override
    public Collection<User> find(User pattern) {
        return super.find(
                pattern,
                User::getId,
                User::getAvatar,
                User::getLogin,
                User::getPassword,
                User::getRole,
                User::getLanguage
        );
    }

}
