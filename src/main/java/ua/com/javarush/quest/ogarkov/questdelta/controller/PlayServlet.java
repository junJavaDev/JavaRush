package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.service.AnswerService;
import ua.com.javarush.quest.ogarkov.questdelta.service.GameService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.Parser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.PLAY)
public class PlayServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -195113263125943009L;
    private final GameService gameService = GameService.INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        long userId = Parser.userId(req);
        Optional<UserDto> optUser = userService.get(userId);
        if (optUser.isPresent()) {
            UserDto user = optUser.get();
            Long questId = formData.getId();
            Optional<QuestDto> optQuest = questService.get(questId);
            if (optQuest.isPresent()) {
                GameDto gameSession = gameService.getGame(formData, user.getId());
                req.setAttribute(S.attrGame, gameSession);
                Jsp.forward(req, resp, S.jspPlay);
            } else if (user.getGames() != null && !user.getGames().isEmpty()) {
                Optional<GameDto> game = gameService.getLastGame(user.getId());
                if (game.isPresent()) {
                    req.setAttribute(S.attrGame, game.get());
                    Jsp.forward(req, resp, S.jspPlay);
                } else Jsp.redirect(req, resp, Go.QUESTS);
            } else Jsp.redirect(req, resp, Go.QUESTS);
        } else Jsp.redirect(req, resp, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long userId = Parser.userId(req);
        long answerId = formData.getLong(S.inputAnswer);
        Optional<AnswerDto> answer = answerService.get(answerId);
        Optional<UserDto> optUser = userService.get(userId);
        if (optUser.isPresent()) {
            UserDto user = optUser.get();
            GameDto game = gameService.getGame(formData, user.getId());
            if (game != null) {
                if (game.getGameState() == GameState.PLAY && answer.isPresent()) {
                    GameDto gameDto = gameService.updateNextQuestion(answer.get(), game);
                    req.setAttribute(S.attrGame, gameDto);
                    Jsp.forward(req, resp, S.jspPlay);
                } else if (answerId == S.playStartAgainValue) {
                    GameDto newGame = gameService.startAgain(game);
                    req.setAttribute(S.attrGame, newGame);
                    Jsp.forward(req, resp, S.jspPlay);
                } else {
                    gameService.complete(game, user.getId());
                }
            }
            Jsp.redirect(req, resp, Go.QUESTS);
        }
    }


}