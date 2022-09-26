package ua.com.javarush.quest.ogarkov.questdelta.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@Builder(builderMethodName = "with")
public class GameSession extends AbstractEntity implements Comparable<GameSession>{
    Long id;
    Long userId;
    Long questId;
    GameState gameState;
    ZonedDateTime startTime;
    ZonedDateTime lastSeen;
    Long currentQuestionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameSession that = (GameSession) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(GameSession o) {
        return Long.compare(id, o.id);
    }
}
