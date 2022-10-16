package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.Role;
import ua.com.javarush.quest.ogarkov.entity.User;
import ua.com.javarush.quest.ogarkov.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.repository.Repository;
import ua.com.javarush.quest.ogarkov.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@Loggable(value = Loggable.DEBUG)
public enum UserService {

    INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final PasswordService passwordService = PasswordService.INSTANCE;
    private final Setting S = Setting.get();

    public Collection<User> find(User pattern) {
        return userRepository.find(pattern);
    }

    public Optional<UserDto> get(long id) {
        User user = userRepository.get(id);
        return Mapper.user.dtoOf(user);
    }

    public void uploadAvatar(Part data, long userId) throws IOException {
        String avatar = S.usersDir + userId + Parser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(avatar, data.getInputStream());
        if (isUploaded) {
            userRepository.get(userId).setAvatar(avatar);
        }
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public long create(FormData formData) {
        User user = User.with().id(formData.getId()).login(formData.getParameter(S.inputLogin)).password(passwordService.getHash(formData.getParameter(S.inputPassword))).role(Role.valueOf(formData.getParameter(S.inputRole))).build();
        userRepository.create(user);
        return user.getId();
    }

    public void update(FormData formData, long userId) {
        User parsed = Mapper.user.parse(formData);
        User user = userRepository.get(userId);
        if (!parsed.getLogin().isEmpty()) {
            user.setLogin(parsed.getLogin());
        }
        if (!parsed.getPassword().isEmpty()) {
            user.setPassword(passwordService.getHash(parsed.getPassword()));
        }
        if (parsed.getRole() != null) {
            user.setRole(parsed.getRole());
        }
    }

    public void changeLang(UserDto userDto) {
        Long id = userDto.getId();
        User user = userRepository.get(id);
        user.setLanguage(userDto.getLanguage());
    }

    public void delete(long userId) {
        userRepository.delete(userRepository.get(userId));
    }
}
