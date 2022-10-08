package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.*;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebServlet(name = "statisticServlet", value = Go.STATISTICS)
public class StatisticServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 6316268318161580029L;
    UserService userService = UserService.INSTANCE;
    GameSessionService gameSessionService = GameSessionService.INSTANCE;
    AnswerService answerService = AnswerService.INSTANCE;
    QuestionService questionService = QuestionService.INSTANCE;
    QuestService questService = QuestService.INSTANCE;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long usersRegistered = userService.getAll().size();
        long gamesPlayed = gameSessionService.getAll().size();
        long questCreated = questService.getAll().size();
        long questionsCreated = questionService.getAll().size();
        long answersCreated = answerService.getAll().size();

        Map.Entry<Long, List<GameSession>> bestPlayer = getLeader(GameSession.with().gameState(GameState.WIN).build(), GameSession::getUserId);
        Map.Entry<Long, List<GameSession>> worstPlayer = getLeader(GameSession.with().gameState(GameState.LOSE).build(), GameSession::getUserId);
        Map.Entry<Long, List<GameSession>> mostPopularQuest = getLeader(GameSession.with().build(), GameSession::getQuestId);
        Long bestPlayerID = bestPlayer.getKey();
        Long worstPlayerID = worstPlayer.getKey();
        Long mostPopularQuestID = mostPopularQuest.getKey();
        Optional<User> optBestPlayer = userService.get(bestPlayerID);
        Optional<User> optWorstPlayer = userService.get(worstPlayerID);
        Optional<Quest> optMostPopularQuest = questService.get(mostPopularQuestID);
        String bestPlayerLogin = optBestPlayer.isPresent()
                ? optBestPlayer.get().getLogin()
                : "Not exist";
        String worstPlayerLogin = optWorstPlayer.isPresent()
                ? optWorstPlayer.get().getLogin()
                : "Not exist";
        String mostPopularQuestName = optMostPopularQuest.isPresent()
                ? optMostPopularQuest.get().getName()
                : "Not exist";
        long bestPlayerWins = bestPlayer.getValue().size();
        long worstPlayerLoses = worstPlayer.getValue().size();
        long mostPopularQuestLaunches = mostPopularQuest.getValue().size();

        request.setAttribute("usersRegistered", usersRegistered);
        request.setAttribute("gamesPlayed", gamesPlayed);
        request.setAttribute("questCreated", questCreated);
        request.setAttribute("questionsCreated", questionsCreated);
        request.setAttribute("answersCreated", answersCreated);
        request.setAttribute("bestPlayerID", bestPlayerID);
        request.setAttribute("worstPlayerID", worstPlayerID);
        request.setAttribute("mostPopularQuestID", mostPopularQuestID);
        request.setAttribute("bestPlayerLogin", bestPlayerLogin);
        request.setAttribute("worstPlayerLogin", worstPlayerLogin);
        request.setAttribute("mostPopularQuestName", mostPopularQuestName);
        request.setAttribute("bestPlayerWins", bestPlayerWins);
        request.setAttribute("worstPlayerLoses", worstPlayerLoses);
        request.setAttribute("mostPopularQuestLaunches", mostPopularQuestLaunches);
        Jsp.forward(request, response, "/statistics");
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


}