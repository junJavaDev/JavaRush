package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public enum UserService {

    INSTANCE;

    private final Repository<User> userRepository = new UserRepository();

    public Collection<User> find(User pattern) {
        return userRepository.find(pattern);
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Collection<User> getAll(int pageNumber, int pageSize) {
        return userRepository.getAll(pageNumber, pageSize);
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public void update(User user) {
        userRepository.update(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }
}
