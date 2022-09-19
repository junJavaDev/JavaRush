package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;

import java.util.Collection;

public class GameSessionRepository extends AbstractRepository<GameSession> {
    @Override
    public Collection<GameSession> find(GameSession pattern) {
        return super.find(
                pattern,
                GameSession::getId,
                GameSession::getUserId,
                GameSession::getQuestId,
                GameSession::getGameImage,
                GameSession::getGameState,
                GameSession::getStartTime,
                GameSession::getCurrentQuestionId
        );
    }
}
