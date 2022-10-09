package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.repository.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Optional;

public enum GameSessionService {

    INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();

    public GameSession getGame(Quest quest, User user) {
        GameSession gameSession;

        Optional<GameSession> optGameSession =
                find(GameSession.with()
                        .questId(quest.getId())
                        .userId(user.getId())
                        .build())
                        .stream()
                        .max(GameSession::compareTo);

        if (optGameSession.isPresent()
                && user.getGameSessions().contains(optGameSession.get())) {
            gameSession = optGameSession.get();
            gameSession.setLastSeen(ZonedDateTime.now());
        } else {
            gameSession = getNew(quest, user.getId());
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
        Long firstQuestionId = quest.getFirstQuestionId();
        Question firstQuestion = questionRepository.get(firstQuestionId).orElseThrow();
        GameSession gameSession = GameSession.with()
                .questId(quest.getId())
                .userId(userId)
                .gameState(firstQuestion.getGameState())
                .currentQuestionId(firstQuestionId)
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

    public GameSession updateGameSession(GameSession currentGameSession) {
        long userId = currentGameSession.getUserId();
        long questId = currentGameSession.getQuestId();
        User user = userRepository.get(userId).orElseThrow();
        Quest quest = questRepository.get(questId).orElseThrow();
        Collection<GameSession> userGameSessions = user.getGameSessions();
        userGameSessions.remove(currentGameSession);
        GameSession newGameSession = getNew(quest, userId);
        userGameSessions.add(newGameSession);
        return newGameSession;
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
