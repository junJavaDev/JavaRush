package ua.com.javarush.quest.ogarkov.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(builderMethodName = "with")
public class QuestDto {
    Long id;
    String name;
    Long authorId;
    String text;
    String image;
    Long firstQuestionId;
    List<QuestionDto> questions;

    @Override
    public String toString() {
        return "QuestDto{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", firstQuestionId=" + firstQuestionId +
                '}';
    }
}
