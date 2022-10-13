package ua.com.javarush.quest.ogarkov.questdelta.dto;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;

@Data
@Builder(builderMethodName = "with")
public class GameDto {
    Long id;
    Long userId;
    Long questId;
    GameState gameState;
    QuestionDto question;
}
