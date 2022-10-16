package ua.com.javarush.quest.ogarkov.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.ogarkov.entity.GameState;

import java.util.List;

@Data
@Builder(builderMethodName = "with")
public class QuestionDto {
    Long id;
    Long questId;
    GameState gameState;
    String name;
    String text;
    String image;
    List<AnswerDto> answers;

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", questId=" + questId +
                '}';
    }
}
