package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.GameSessionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public enum GameSessionService {

    INSTANCE;

    public GameSession getGame(Quest quest, User user) {
        GameSession gameSession;

        Optional<GameSession> optGameSession =
                find(GameSession.with()
                        .questId(quest.getId())
                        .userId(user.getId())
                        .build())
                        .stream()
                        .max(GameSession::compareTo);

        if (optGameSession.isPresent()) {
            gameSession = optGameSession.get();
            gameSession.setLastSeen(ZonedDateTime.now());
        } else {
            gameSession = getNew(quest, user.getId());
            checkInitGameSessions(user);
            user.getGameSessions().add(gameSession);
        }
        return gameSession;
    }

    public GameSession getLastGame(User user) {
        return find(GameSession.with()
                .userId(user.getId())
                .build())
                .stream()
                .max(Comparator.comparing(gs -> gs.getLastSeen().truncatedTo(ChronoUnit.MILLIS)))
                .orElseThrow();
    }

    public GameSession getNew(Quest quest, long userId) {
        GameSession gameSession = GameSession.with()
                .questId(quest.getId())
                .userId(userId)
                .gameState(GameState.PLAY)
                .currentQuestionId(quest.getFirstQuestionId())
                .startTime(ZonedDateTime.now())
                .lastSeen(ZonedDateTime.now())
                .build();
        create(gameSession);
        return gameSession;
    }

    public Collection<GameSession> getAll(Long userId, GameState gameState) {
        return gameSessionRepository.find(GameSession.with()
                .userId(userId)
                .gameState(gameState)
                .build());
    }

    private void checkInitGameSessions(User user) {
        if (user.getGameSessions() == null) {
            user.setGameSessions(new HashSet<>());
        }
    }

    private final Repository<GameSession> gameSessionRepository = GameSessionRepository.getInstance();

    public Collection<GameSession> find(GameSession pattern) {
        return gameSessionRepository.find(pattern);
    }

    public Optional<GameSession> get(long id) {
        return gameSessionRepository.get(id);
    }

    public Collection<GameSession> getAll() {
        return gameSessionRepository.getAll();
    }

    public void create(GameSession gameSession) {
        gameSessionRepository.create(gameSession);
    }

    public void update(GameSession gameSession) {
        gameSessionRepository.update(gameSession);
    }

    public void delete(GameSession gameSession) {
        gameSessionRepository.delete(gameSession);
    }


}
