package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.repository.GameSessionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum GameSessionService {

    INSTANCE;

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
