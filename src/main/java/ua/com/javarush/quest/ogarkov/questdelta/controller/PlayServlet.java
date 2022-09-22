package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.service.GameSessionService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static jakarta.servlet.http.HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.LOGIN;
import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.PLAY;

@WebServlet(name = "playServlet", value = PLAY)
public class PlayServlet extends HttpServlet {

    GameSessionService gameSessionService = GameSessionService.INSTANCE;
    QuestService questService = QuestService.INSTANCE;
    QuestionService questionService = QuestionService.INSTANCE;

    @Serial
    private static final long serialVersionUID = -195113263125943009L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String inputQuestId = request.getParameter("questId");
        String inputQuestionId = request.getParameter("questionId");

        if (!isQuestCorrect(inputQuestId)) {
            response.sendError(SC_INTERNAL_SERVER_ERROR);
            return;
        }

        HttpSession session = request.getSession();
        Object rawUser = session.getAttribute("user");
        if (rawUser == null) {
            Jsp.redirect(response, LOGIN);
            return;
        }

        User user = (User) rawUser;
        long questId = Long.parseLong(inputQuestId);
        Quest quest = questService.get(questId).orElseThrow();

        GameSession gameSession;

        Optional<GameSession> optGameSession = gameSessionService
                .find(GameSession.with()
                        .questId(questId)
                        .userId(user.getId())
                        .build())
                .stream()
                .findFirst();

        if (optGameSession.isPresent()) {
            gameSession = optGameSession.get();
        } else {
            gameSession = GameSession.with()
                    .questId(questId)
                    .userId(user.getId())
                    .gameState(GameState.PLAY)
                    .currentQuestionId(quest.getFirstQuestionId())
                    .startTime(ZonedDateTime.now())
                    .lastSeen(ZonedDateTime.now())
                    .build();
            checkInitGameSessions(user);
            user.getGameSessions().add(gameSession);
            gameSessionService.create(gameSession);
        }

        long currentQuestionId = gameSession.getCurrentQuestionId();

        if (isQuestionCorrect(questId, inputQuestionId)) {
            currentQuestionId = Long.parseLong(inputQuestionId);
            gameSession.setCurrentQuestionId(currentQuestionId);
        }


        Question question = questionService
                .get(currentQuestionId)
                .orElseThrow();

        gameSession.setGameState(question.getGameState());


        request.setAttribute("question", question);
        request.setAttribute("gameSession", gameSession);
        Jsp.forward(request, response, PLAY);
    }

    private void checkInitGameSessions(User user) {
        if (user.getGameSessions() == null) {
            user.setGameSessions(new ArrayList<>());
        }
    }

    private boolean isQuestCorrect(String inputQuestId) {
        long id = positiveLong(inputQuestId);
        return questService.get(id).isPresent();
    }

    private boolean isQuestionCorrect(long questId, String inputQuestionId) {
        long idQuestion = positiveLong(inputQuestionId);
        Optional<Question> optQuestion = questionService.get(idQuestion);
        return optQuestion.filter(question ->
                question.getQuestId() == questId
        ).isPresent();
    }

    private long positiveLong(String param) {
        long longParam = 0;
        if (param == null || param.isEmpty()) return longParam;

        try {
            longParam = Long.parseLong(param);
        } catch (NumberFormatException ignored) {
        }
        return longParam;
    }
}