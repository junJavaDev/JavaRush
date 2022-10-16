package ua.com.javarush.quest.ogarkov.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.ogarkov.entity.GameState;

@Data
@Builder(builderMethodName = "with")
public class GameDto {
    Long id;
    Long userId;
    Long questId;
    GameState gameState;
    QuestionDto question;

    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", questId=" + questId +
                '}';
    }
}
