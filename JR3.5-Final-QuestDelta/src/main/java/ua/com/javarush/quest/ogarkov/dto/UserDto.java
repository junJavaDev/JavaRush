package ua.com.javarush.quest.ogarkov.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.ogarkov.entity.Language;
import ua.com.javarush.quest.ogarkov.entity.Role;

import java.util.Collection;

@Data
@Builder(builderMethodName = "with")
public class UserDto {
    Long id;
    String avatar;
    String login;
    Role role;
    @Builder.Default
    Language language = Language.EN;
    long quests;
    Collection<GameDto> games;

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role=" + role +
                '}';
    }
}
