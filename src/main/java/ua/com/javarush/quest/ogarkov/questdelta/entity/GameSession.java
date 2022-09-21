package ua.com.javarush.quest.ogarkov.questdelta.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class GameSession extends AbstractEntity{
    Long id;
    Long userId;
    Long questId;
    GameState gameState;
    ZonedDateTime startTime;
    ZonedDateTime lastSeen;
    Long currentQuestionId;
}
