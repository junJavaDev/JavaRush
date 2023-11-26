package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import org.apache.commons.codec.binary.Base64;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.User;
import ua.com.javarush.quest.ogarkov.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.repository.Repository;
import ua.com.javarush.quest.ogarkov.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.Optional;

// Salt can be added to this class for better protection
@Loggable(value = Loggable.DEBUG)
public enum PasswordService {

    INSTANCE;
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final Setting S = Setting.get();

    public String getHash(String password) {
        return hash(password);
    }

    public Optional<UserDto> check(String login, String password) {
        String passwordHash = hash(password);
        Optional<User> optionalUser = userRepository
                .find(User.with()
                        .login(login)
                        .password(passwordHash)
                        .build())
                .stream()
                .findFirst();
        if (optionalUser.isPresent()) {
            return Mapper.user.dtoOf(optionalUser.get());
        } else return Optional.empty();
    }

    private String hash(String password) {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException(S.emptyPassword);
        return Base64.encodeBase64String(password.getBytes());
    }
}
