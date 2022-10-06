package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.service.AnswerService;
import ua.com.javarush.quest.ogarkov.questdelta.service.GameSessionService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@WebServlet(name = "playServlet", value = PLAY)
public class PlayServlet extends HttpServlet {

    GameSessionService gameSessionService = GameSessionService.INSTANCE;
    QuestService questService = QuestService.INSTANCE;
    QuestionService questionService = QuestionService.INSTANCE;
    AnswerService answerService = AnswerService.INSTANCE;

    @Serial
    private static final long serialVersionUID = -195113263125943009L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long questId = ReqParser.getLong(req, "id");
        Optional<User> optUser = ReqParser.getUser(req);

        if (optUser.isPresent()) {
            User user = optUser.get();
            if (questId > 0) {
                Optional<Quest> optQuest = questService.get(questId);
                if (optQuest.isPresent()) {
                    Quest quest = optQuest.get();
                    GameSession gameSession = gameSessionService.getGame(quest, user);
                    if (gameSession.getGameState() != GameState.PLAY) {
                        gameSession = updateGameSession(user, quest, gameSession);
                    }

                    long currentQuestionId = gameSession.getCurrentQuestionId();

                        Question question = questionService
                                .get(currentQuestionId)
                                .orElseThrow();

                        gameSession.setGameState(question.getGameState());
                        req.setAttribute("question", question);
                        req.setAttribute("quest", quest);
                        Jsp.forward(req, resp, "/play");
                } else Jsp.redirect(req, resp, QUESTS);
            } else if (!user.getGameSessions().isEmpty()) {
                GameSession gameSession = gameSessionService.getLastGame(user);
                long currentQuestionId = gameSession.getCurrentQuestionId();

                Question question = questionService
                        .get(currentQuestionId)
                        .orElseThrow();

                gameSession.setGameState(question.getGameState());
                req.setAttribute("question", question);
                req.setAttribute("quest", questService.get(gameSession.getQuestId()).orElseThrow());
                Jsp.forward(req, resp, "/play");
            } else Jsp.redirect(req, resp, QUESTS);
        } else Jsp.redirect(req, resp, LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long questId = ReqParser.getLong(req, "id");

        User user = ReqParser.getUser(req).orElseThrow();
        Quest quest = questService.get(questId).orElseThrow();
        GameSession gameSession = gameSessionService.getGame(quest, user);


        if (gameSession.getGameState() == GameState.PLAY) {
            Optional<Answer> answer = answerService.get(Long.parseLong(req.getParameter("answer")));
            if (answer.isPresent()) {
                long nextQuestionId = answer.get().getNextQuestionId();
                Question question = questionService.get(nextQuestionId).orElseThrow();
                gameSession.setCurrentQuestionId(nextQuestionId);
                gameSession.setGameState(question.getGameState());
                req.setAttribute("question", question);
                req.setAttribute("quest", quest);
                Jsp.forward(req, resp, "/play");
            } else {
                Jsp.redirect(req, resp, PLAY + "?id=" + questId);
            }

        } else {
            long answerId = Long.parseLong(req.getParameter("answer"));
            if (answerId == -1) {
                GameSession gs = updateGameSession(user, quest, gameSession);
                long currentQuestionId = gs.getCurrentQuestionId();

                Question question = questionService
                        .get(currentQuestionId)
                        .orElseThrow();

                gs.setGameState(question.getGameState());
                req.setAttribute("question", question);
                req.setAttribute("quest", quest);
                Jsp.forward(req, resp, "/play");
            } else if (answerId == -2) {
               user.getGameSessions().remove(gameSession);
               Jsp.redirect(req, resp, QUESTS);
            } else Jsp.redirect(req, resp, PLAY + "?id=" + questId);

        }
    }

    private GameSession updateGameSession(User user, Quest quest, GameSession gameSession) {
        user.getGameSessions().remove(gameSession);
        GameSession gs = gameSessionService.getNew(quest, user.getId());
        user.getGameSessions().add(gs);
        return gs;
    }


}