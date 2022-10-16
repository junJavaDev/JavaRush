package ua.com.javarush.quest.ogarkov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.dto.*;
import ua.com.javarush.quest.ogarkov.entity.GameState;
import ua.com.javarush.quest.ogarkov.service.AnswerService;
import ua.com.javarush.quest.ogarkov.service.GameService;
import ua.com.javarush.quest.ogarkov.service.QuestService;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

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
    private static final Logger log = LoggerFactory.getLogger(PlayServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Open page: {}, userID: {}", Go.PLAY, Parser.userId(req));
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