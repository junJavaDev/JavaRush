package ua.com.javarush.quest.ogarkov.questdelta.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.*;

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
    @Builder.Default
    Collection<Quest> quests = new ArrayList<>();
    @Builder.Default
    Collection<GameSession> gameSessions = new HashSet<>();

}
