package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.Game;

import java.util.Collection;

public class GameRepository extends AbstractRepository<Game> {

    private GameRepository() {
    }

    public static GameRepository getInstance() {
        return GameSessionRepositoryHolder.HOLDER_INSTANCE;
    }

    @Override
    public Collection<Game> find(Game pattern) {
        return super.find(
                pattern,
                Game::getId,
                Game::getUserId,
                Game::getQuestId,
                Game::getGameState,
                Game::getStartTime,
                Game::getCurrentQuestionId
        );
    }

    public static class GameSessionRepositoryHolder {
        public static final GameRepository HOLDER_INSTANCE = new GameRepository();
    }
}
