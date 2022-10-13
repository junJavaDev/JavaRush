package ua.com.javarush.quest.ogarkov.questdelta.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "with")
public class AnswerDto {
    Long id;
    Long questionId;
    Long nextQuestionId;
    String text;
}
