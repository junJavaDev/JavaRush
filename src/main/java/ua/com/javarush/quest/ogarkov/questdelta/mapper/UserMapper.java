package ua.com.javarush.quest.ogarkov.questdelta.mapper;

import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.GameDto;
import ua.com.javarush.quest.ogarkov.questdelta.dto.UserDto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Collection;
import java.util.Optional;

public class UserMapper implements Mapper<User, UserDto> {

    private final Setting S = Setting.get();

    @Override
    public Optional<UserDto> dtoOf(User user) {
        if (user != null) {
            Collection<GameDto> games = user
                    .getGames()
                    .stream()
                    .map(Mapper.game::dtoOf)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return Optional.of(UserDto.with()
                    .id(user.getId())
                    .login(user.getLogin())
                    .avatar(user.getAvatar())
                    .language(user.getLanguage())
                    .role(user.getRole())
                    .games(games)
                    .quests(user.getQuests().size())
                    .build());
        } else return Optional.empty();
    }

    @Override
    public User parse(FormData formData) {
        User user = User.with()
                .id(formData.getId())
                .login(formData.getParameter(S.inputLogin))
                .password(formData.getParameter(S.inputPassword))
                .build();
        String inputRole = formData.getParameter(S.inputRole);
        if (!inputRole.isEmpty()) {
            user.setRole(Role.valueOf(inputRole));
        }
        return user;
    }
}
