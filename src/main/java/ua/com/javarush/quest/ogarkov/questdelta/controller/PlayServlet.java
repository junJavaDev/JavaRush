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
import ua.com.javarush.quest.ogarkov.questdelta.service.GameSessionService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.PLAY;

@WebServlet(name = "playServlet", value = PLAY)
public class PlayServlet extends HttpServlet {

    GameSessionService gameSessionService = GameSessionService.INSTANCE;
    QuestService questService = QuestService.INSTANCE;

    @Serial
    private static final long serialVersionUID = -195113263125943009L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Quest quest = questService.getAll().stream().findFirst().orElseThrow();
        User currentUser = (User) request.getSession().getAttribute("user");
        GameSession gameSession;

        if (currentUser.getGameSessions() == null) {
            currentUser.setGameSessions(new ArrayList<>());
        }

        Optional<GameSession> optGameSession = gameSessionService.find(
                GameSession.with()
                        .questId(quest.getId())
                        .userId(currentUser.getId())
                        .build()
        ).stream().findFirst();

        if (optGameSession.isPresent()) {
            gameSession = optGameSession.get();
        } else {
            gameSession = GameSession.with()
                    .questId(quest.getId())
                    .userId(currentUser.getId())
                    .gameState(GameState.PLAY)
                    .currentQuestionId(quest.getQuestions().stream().findFirst().orElseThrow().getId())
                    .startTime(ZonedDateTime.now())
                    .lastSeen(ZonedDateTime.now())
                    .build();
            currentUser.getGameSessions().add(gameSession);
            gameSessionService.create(gameSession);
        }

        request.getSession().setAttribute("quest", quest);
        request.getSession().setAttribute("gameSession", gameSession);


        Jsp.forward(request, response, PLAY);
    }
}