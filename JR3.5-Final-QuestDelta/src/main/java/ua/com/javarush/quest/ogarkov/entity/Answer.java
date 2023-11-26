package ua.com.javarush.quest.ogarkov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Answer extends AbstractEntity {
    Long id;
    Long questionId;
    Long nextQuestionId;
    String text;

    public static Answer empty() {
        return Answer.with().build();
    }
}
