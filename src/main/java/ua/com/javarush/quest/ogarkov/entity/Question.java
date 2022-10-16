package ua.com.javarush.quest.ogarkov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Question extends AbstractEntity {
    final List<Answer> answers = new ArrayList<>();
    Long id;
    Long questId;
    GameState gameState;
    String name;
    String text;
    String image;

    public static Question empty() {
        return Question.with().build();
    }
}
