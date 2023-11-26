package ua.com.javarush.quest.ogarkov.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "with")
public class AnswerDto {
    Long id;
    Long questionId;
    Long nextQuestionId;
    String text;

    @Override
    public String toString() {
        return "AnswerDto{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", nextQuestionId=" + nextQuestionId +
                '}';
    }
}
