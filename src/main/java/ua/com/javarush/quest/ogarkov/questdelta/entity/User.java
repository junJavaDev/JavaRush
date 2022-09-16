package ua.com.javarush.quest.ogarkov.questdelta.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class User extends AbstractEntity {

    Long id;
    String login;
    String password;
    String avatar;
    Role role;

}
