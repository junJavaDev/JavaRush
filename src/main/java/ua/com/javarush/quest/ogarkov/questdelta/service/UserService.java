package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public enum UserService {

    INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final Setting S = Setting.get();

    public Collection<User> find(User pattern) {
        return userRepository.find(pattern);
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public void uploadAvatar(Part data, User user) throws IOException {
        String avatar = S.usersDir + user.getId() + ReqParser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(avatar, data.getInputStream());
        if (isUploaded) {
            user.setAvatar(avatar);
        }
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
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
