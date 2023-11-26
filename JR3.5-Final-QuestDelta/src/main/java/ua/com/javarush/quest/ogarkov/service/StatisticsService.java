package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import ua.com.javarush.quest.ogarkov.dto.DataTank;
import ua.com.javarush.quest.ogarkov.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.entity.Game;
import ua.com.javarush.quest.ogarkov.entity.GameState;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Loggable(value = Loggable.DEBUG)
public enum StatisticsService {
    INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final GameService gameService = GameService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final Setting S = Setting.get();

    public DataTank getStatistics() {
        DataTank statistics = DataTank.empty();
        var bestPlayer = getBestPlayer();
        var worstPlayer = getWorstPlayer();
        var mostPopularQuest = getMostPopularQuest();
        Long bestPlayerID = bestPlayer.getKey();
        Long worstPlayerID = worstPlayer.getKey();
        Long mostPopularQuestID = mostPopularQuest.getKey();

        statistics.addAttr(S.attrUsersRegistered, userService.getAll().size());
        statistics.addAttr(S.attrGamesPlayed, gameService.getAll().size());
        statistics.addAttr(S.attrQuestCreated, questService.getAll().size());
        statistics.addAttr(S.attrQuestionsCreated, questionService.getAll().size());
        statistics.addAttr(S.attrAnswersCreated, answerService.getAll().size());
        statistics.addAttr(S.attrBestPlayerId, bestPlayerID);
        statistics.addAttr(S.attrWorstPlayerId, worstPlayerID);
        statistics.addAttr(S.attrMostPopularQuestId, mostPopularQuestID);
        statistics.addAttr(S.attrBestPlayerLogin, getLogin(bestPlayerID));
        statistics.addAttr(S.attrWorstPlayerLogin, getLogin(worstPlayerID));
        statistics.addAttr(S.attrMostPopularQuestName, getQuestName(mostPopularQuestID));
        statistics.addAttr(S.attrBestPlayerWins, bestPlayer.getValue().size());
        statistics.addAttr(S.attrWorstPlayerLoses, worstPlayer.getValue().size());
        statistics.addAttr(S.attrMostPopularQuestLaunches, mostPopularQuest.getValue().size());
        return statistics;
    }

    private String getLogin(long id) {
        Optional<UserDto> optionalUser = userService.get(id);
        return optionalUser.isPresent()
                ? optionalUser.get().getLogin()
                : S.notExist;
    }

    private String getQuestName(long id) {
        Optional<QuestDto> optionalQuest = questService.get(id);
        return optionalQuest.isPresent()
                ? optionalQuest.get().getName()
                : S.notExist;
    }

    private Map.Entry<Long, List<Game>> getLeader(Game pattern, Function<Game, Long> function) {
        return gameService.find(pattern)
                .stream()
                .collect(Collectors.groupingBy(function))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(entry -> entry.getValue().size()))
                .orElse(Map.entry(0L, Collections.emptyList()));
    }

    private Map.Entry<Long, List<Game>> getBestPlayer() {
        return getLeader(
                Game.with()
                        .gameState(GameState.WIN)
                        .build(),
                Game::getUserId
        );
    }

    private Map.Entry<Long, List<Game>> getWorstPlayer() {
        return getLeader(
                Game.with()
                        .gameState(GameState.LOSE)
                        .build(),
                Game::getUserId
        );
    }

    private Map.Entry<Long, List<Game>> getMostPopularQuest() {
        return getLeader(
                Game.with().build(),
                Game::getQuestId
        );
    }
}
