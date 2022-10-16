package ua.com.javarush.quest.ogarkov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Quest extends AbstractEntity {
    Long id;
    String name;
    Long authorId;
    String text;
    String image;
    Long firstQuestionId;
    @Builder.Default
    List<Question> questions = new ArrayList<>();

    public static Quest empty() {
        return Quest.with().build();
    }
}
