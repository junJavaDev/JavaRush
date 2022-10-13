package ua.com.javarush.quest.ogarkov.questdelta.mapper;

import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.GameDto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Game;

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
