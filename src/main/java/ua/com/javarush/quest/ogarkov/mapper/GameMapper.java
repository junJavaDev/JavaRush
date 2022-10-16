package ua.com.javarush.quest.ogarkov.mapper;

import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.entity.Game;
import ua.com.javarush.quest.ogarkov.dto.GameDto;

import java.util.Optional;

public class GameMapper implements Mapper<Game, GameDto> {
    @Override
    public Optional<GameDto> dtoOf(Game game) {
        return game != null
                ? Optional.of(GameDto.with()
                .id(game.getId())
                .questId(game.getQuestId())
                .userId(game.getUserId())
                .gameState(game.getGameState())
                .build())
                : Optional.empty();
    }

    @Override
    public Game parse(FormData formData) {
        return null;
    }
}
