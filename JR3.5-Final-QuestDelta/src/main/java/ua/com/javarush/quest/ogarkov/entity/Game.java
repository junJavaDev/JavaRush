package ua.com.javarush.quest.ogarkov.entity;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Objects;

@Data
@Builder(builderMethodName = "with")
public class Game extends AbstractEntity implements Comparable<Game> {
    Long id;
    Long userId;
    Long questId;
    GameState gameState;
    ZonedDateTime startTime;
    ZonedDateTime lastSeen;
    Long currentQuestionId;

    public static Game empty() {
        return Game.with().build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game that = (Game) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Game o) {
        return Long.compare(id, o.id);
    }
}
