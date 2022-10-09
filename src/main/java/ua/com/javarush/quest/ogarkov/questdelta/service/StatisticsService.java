package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.dto.DataTank;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum StatisticsService {
    INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final GameSessionService gameSessionService = GameSessionService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final Setting S = Setting.get();

    public DataTank getStatistics() {
        DataTank statistics = DataTank.empty();
        long usersRegistered = userService.getAll().size();
        long gamesPlayed = gameSessionService.getAll().size();
        long questCreated = questService.getAll().size();
        long questionsCreated = questionService.getAll().size();
        long answersCreated = answerService.getAll().size();
        var bestPlayer = getBestPlayer();
        var worstPlayer = getWorstPlayer();
        var mostPopularQuest = getMostPopularQuest();
        Long bestPlayerID = bestPlayer.getKey();
        Long worstPlayerID = worstPlayer.getKey();
        Long mostPopularQuestID = mostPopularQuest.getKey();
        Optional<User> optBestPlayer = userService.get(bestPlayer.getKey());
        Optional<User> optWorstPlayer = userService.get(worstPlayer.getKey());
        Optional<Quest> optMostPopularQuest = questService.get(mostPopularQuest.getKey());
        String bestPlayerLogin = optBestPlayer.isPresent()
                ? optBestPlayer.get().getLogin()
                : S.notExist;
        String worstPlayerLogin = optWorstPlayer.isPresent()
                ? optWorstPlayer.get().getLogin()
                : S.notExist;
        String mostPopularQuestName = optMostPopularQuest.isPresent()
                ? optMostPopularQuest.get().getName()
                : S.notExist;
        long bestPlayerWins = bestPlayer.getValue().size();
        long worstPlayerLoses = worstPlayer.getValue().size();
        long mostPopularQuestLaunches = mostPopularQuest.getValue().size();

        statistics.addAttribute(S.attrUsersRegistered, usersRegistered);
        statistics.addAttribute(S.attrUsersRegistered, usersRegistered);
        statistics.addAttribute(S.attrGamesPlayed, gamesPlayed);
        statistics.addAttribute(S.attrQuestCreated, questCreated);
        statistics.addAttribute(S.attrQuestionsCreated, questionsCreated);
        statistics.addAttribute(S.attrAnswersCreated, answersCreated);
        statistics.addAttribute(S.attrBestPlayerId, bestPlayerID);
        statistics.addAttribute(S.attrWorstPlayerId, worstPlayerID);
        statistics.addAttribute(S.attrMostPopularQuestId, mostPopularQuestID);
        statistics.addAttribute(S.attrBestPlayerLogin, bestPlayerLogin);
        statistics.addAttribute(S.attrWorstPlayerLogin, worstPlayerLogin);
        statistics.addAttribute(S.attrMostPopularQuestName, mostPopularQuestName);
        statistics.addAttribute(S.attrBestPlayerWins, bestPlayerWins);
        statistics.addAttribute(S.attrWorstPlayerLoses, worstPlayerLoses);
        statistics.addAttribute(S.attrMostPopularQuestLaunches, mostPopularQuestLaunches);
        return statistics;
    }

    private Map.Entry<Long, List<GameSession>> getLeader(GameSession pattern, Function<GameSession, Long> function) {
        return gameSessionService.find(pattern)
                .stream()
                .collect(Collectors.groupingBy(function))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(entry -> entry.getValue().size()))
                .orElse(Map.entry(0L, Collections.emptyList()));
    }

    private Map.Entry<Long, List<GameSession>> getBestPlayer() {
        return getLeader(
                GameSession.with()
                        .gameState(GameState.WIN)
                        .build(),
                GameSession::getUserId
        );
    }

    private Map.Entry<Long, List<GameSession>> getWorstPlayer() {
        return getLeader(
                GameSession.with()
                        .gameState(GameState.LOSE)
                        .build(),
                GameSession::getUserId
        );
    }

    private Map.Entry<Long, List<GameSession>> getMostPopularQuest() {
        return getLeader(
                GameSession.with().build(),
                GameSession::getQuestId
        );
    }
}
