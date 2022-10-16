package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.User;

import java.util.*;

public class UserRepository extends AbstractRepository<User>{

    private static class UserRepositoryHolder {
        public static final UserRepository HOLDER_INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return UserRepository.UserRepositoryHolder.HOLDER_INSTANCE;
    }

    private UserRepository() {
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

    @Override
    public Collection<User> find(User pattern) {
        return super.find(
                pattern,
                User::getId,
                User::getAvatar,
                User::getLogin,
                User::getPassword,
                User::getRole
        );
    }

}
