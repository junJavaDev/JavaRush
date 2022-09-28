package ua.com.javarush.quest.ogarkov.questdelta.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

@Data
@Builder(builderMethodName = "with")
public class Question extends AbstractEntity{
    Long id;
    Long questId;
    GameState gameState;
    String name;
    String text;
    String image;
    @Builder.Default
    List<Answer> answers = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) && Objects.equals(questId, question.questId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questId);
    }
}
