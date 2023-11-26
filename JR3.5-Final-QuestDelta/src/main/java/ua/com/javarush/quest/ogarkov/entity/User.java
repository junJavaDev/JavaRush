package ua.com.javarush.quest.ogarkov.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class User extends AbstractEntity {

    Long id;
    String avatar;
    String login;
    String password;
    Role role;
    @Builder.Default
    Language language = Language.EN;
    final Collection<Quest> quests = new ArrayList<>();
    final Collection<Game> games = new HashSet<>();

    public static User empty() {
        return User.with().build();
    }
}
