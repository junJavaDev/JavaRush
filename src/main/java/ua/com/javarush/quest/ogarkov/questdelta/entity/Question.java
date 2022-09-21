package ua.com.javarush.quest.ogarkov.questdelta.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Question extends AbstractEntity{
    Long id;
    Long questId;
    GameState gameState;
    String name;
    String text;
    String image;
    Collection<Answer> answers;
}
