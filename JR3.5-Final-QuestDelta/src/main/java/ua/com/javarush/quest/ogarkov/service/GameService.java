package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.entity.*;
import ua.com.javarush.quest.ogarkov.dto.AnswerDto;
import ua.com.javarush.quest.ogarkov.dto.GameDto;
import ua.com.javarush.quest.ogarkov.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.repository.*;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

@Loggable(value = Loggable.DEBUG)
public enum GameService {

    INSTANCE;
    private final UserRepository userRepository = UserRepository.getInstance();
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionRepository questionRepository = QuestionRepository.getInstance();
    private final Repository<Game> gameSessionRepository = GameRepository.getInstance();

    public GameDto getGame(FormData formData, long userId) {
        long questId = formData.getId();
        if (questRepository.get(questId) != null) {
            Optional<Game> optGameSession =
                    find(Game.with()
                            .questId(questId)
                            .userId(userId)
                            .build())
                            .stream()
                            .max(Game::compareTo);
            User user = userRepository.get(userId);
            Game game;
            if (optGameSession.isPresent()
                    && user.getGames().contains(optGameSession.get())) {
                game = optGameSession.get();
                game.setLastSeen(ZonedDateTime.now());
            } else {
                game = getNew(questId, userId);
                user.getGames().add(game);
            }
            return nextStep(Mapper.game.dtoOf(game).orElseThrow());
        } else return null;
    }

    public Optional<GameDto> getLastGame(long userId) {
        Game lastGame = find(Game.with()
                .userId(userId)
                .build())
                .stream()
                .filter(game -> questRepository.get(game.getQuestId()) != null)
                .max(Comparator.comparing(game -> game.getLastSeen().truncatedTo(ChronoUnit.MILLIS)))
                .orElse(null);
        if (lastGame != null) {
            return Optional.of(nextStep(Mapper.game.dtoOf(lastGame).orElseThrow()));
        } else return Optional.empty();
    }

    private Game getNew(long questId, long userId) {
        Quest quest = questRepository.get(questId);
        Long firstQuestionId = quest.getFirstQuestionId();
        Question firstQuestion = questionRepository.get(firstQuestionId);
        Game game = Game.with()
                .questId(quest.getId())
                .userId(userId)
                .gameState(firstQuestion.getGameState())
                .currentQuestionId(firstQuestionId)
                .startTime(ZonedDateTime.now())
                .lastSeen(ZonedDateTime.now())
                .build();
        gameSessionRepository.create(game);
        return game;
    }

    public Collection<Game> getAll(Long userId, GameState gameState) {
        return gameSessionRepository.find(Game.with()
                .userId(userId)
                .gameState(gameState)
                .build());
    }


    public Game update(Game currentGame) {
        long userId = currentGame.getUserId();
        long questId = currentGame.getQuestId();
        User user = userRepository.get(userId);
        Collection<Game> userGames = user.getGames();
        userGames.remove(currentGame);
        Game newGame = getNew(questId, userId);
        userGames.add(newGame);
        return newGame;
    }

    public GameDto updateNextQuestion(AnswerDto currentAnswer, GameDto gameDto) {
        Game game = gameSessionRepository.get(gameDto.getId());
        long nextQuestionId = currentAnswer.getNextQuestionId();
        game.setCurrentQuestionId(nextQuestionId);
        return nextStep(gameDto);
    }

    private GameDto nextStep(GameDto gameDto) {
        Game game = gameSessionRepository.get(gameDto.getId());
        Optional<Question> optQuestion =
                Optional.ofNullable(questionRepository.get(game.getCurrentQuestionId()));
        if (optQuestion.isPresent()) {
            Question question = optQuestion.get();
            game.setGameState(question.getGameState());
            gameDto.setQuestion(Mapper.question.dtoOf(question).orElseThrow());
            return gameDto;
        } else return startAgain(gameDto);
    }

    public GameDto startAgain(GameDto gameSession) {
        Game oldGame = gameSessionRepository.get(gameSession.getId());
        Game newGame = update(oldGame);
        return nextStep(Mapper.game.dtoOf(newGame).orElseThrow());
    }

    public void complete(GameDto gameDto, long userId) {
        Game game = gameSessionRepository.get(gameDto.getId());
        User user = userRepository.get(userId);
        user.getGames().remove(game);
    }

    public Collection<Game> getAll() {
        return gameSessionRepository.getAll();
    }

    public Collection<Game> find(Game pattern) {
        return gameSessionRepository.find(pattern);
    }
}
