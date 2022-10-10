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
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.PLAY)
public class PlayServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -195113263125943009L;
    private final GameSessionService gameSessionService = GameSessionService.INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Optional<User> optUser = ReqParser.getUser(req);
        if (optUser.isPresent()) {
            User user = optUser.get();
            Long questId = ReqParser.getLong(req, S.paramId);
            Optional<Quest> optQuest = questService.get(questId);
            if (optQuest.isPresent()) {
                Quest quest = optQuest.get();
                GameSession gameSession = gameSessionService.getGame(quest, user);
                nextStep(req, resp, gameSession);
            } else if (!user.getGameSessions().isEmpty()) {
                GameSession gameSession = gameSessionService.getLastGame(user);
                nextStep(req, resp, gameSession);
            } else Jsp.redirect(req, resp, Go.QUESTS);
        } else Jsp.redirect(req, resp, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long answerId = ReqParser.getLong(req, S.inputAnswer);
        Optional<Answer> answer = answerService.get(answerId);
        GameSession gameSession = getGameSession(req);
        if (gameSession.getGameState() == GameState.PLAY && answer.isPresent()) {
            updateNextQuestion(answer.get(), gameSession);
            nextStep(req, resp, gameSession);
        } else if (answerId == S.playStartAgainValue) {
            startAgain(req, resp, gameSession);
        } else complete(req, resp, gameSession);
    }

    private void nextStep(HttpServletRequest req, HttpServletResponse resp, GameSession gameSession) throws ServletException, IOException {
        long questId = gameSession.getQuestId();
        Quest quest = questService.get(questId).orElseThrow();
        long currentQuestionId = gameSession.getCurrentQuestionId();
        Optional<Question> optQuestion = questionService.get(currentQuestionId);
        if (optQuestion.isPresent()) {
            Question question = optQuestion.get();
            gameSession.setGameState(question.getGameState());
            req.setAttribute(S.attrQuestion, question);
            req.setAttribute(S.attrQuest, quest);
            Jsp.forward(req, resp, S.jspPlay);
        } else startAgain(req, resp, gameSession);
    }

    private void complete(HttpServletRequest req, HttpServletResponse resp, GameSession gameSession) throws IOException {
        User user = ReqParser.getUser(req).orElseThrow();
        user.getGameSessions().remove(gameSession);
        Jsp.redirect(req, resp, Go.QUESTS);
    }

    private void startAgain(HttpServletRequest req, HttpServletResponse resp, GameSession gameSession) throws ServletException, IOException {
        GameSession newGameSession = gameSessionService.update(gameSession);
        nextStep(req, resp, newGameSession);
    }

    private void updateNextQuestion(Answer currentAnswer, GameSession gameSession) {
        long nextQuestionId = currentAnswer.getNextQuestionId();
        gameSession.setCurrentQuestionId(nextQuestionId);
    }

    private GameSession getGameSession(HttpServletRequest req) {
        long id = ReqParser.getId(req);
        Quest quest =  questService.get(id).orElseThrow();
        User user = ReqParser.getUser(req).orElseThrow();
        return gameSessionService.getGame(quest, user);
    }
}