package ua.com.javarush.quest.ogarkov.questdelta.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class User extends AbstractEntity {

    Long id;
    String avatar;
    String login;
    String password;
    Role role;
    Language language;
    Collection<Quest> quests;
    Collection<GameSession> gameSessions;

}
