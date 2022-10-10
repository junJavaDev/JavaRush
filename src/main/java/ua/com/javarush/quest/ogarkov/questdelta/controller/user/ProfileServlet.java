package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.GameSessionService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(value = Go.PROFILE)
public class ProfileServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 856906541196618136L;
    private final UserService userService = UserService.INSTANCE;
    private final GameSessionService gameSessionService = GameSessionService.INSTANCE;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = ReqParser.getLong(request, S.paramId);
        Optional<User> optUser = userService.get(id);

        if (optUser.isPresent()) {
            User user = optUser.get();
            int wins = gameSessionService.getAll(user.getId(), GameState.WIN).size();
            int loses = gameSessionService.getAll(user.getId(), GameState.LOSE).size();
            request.setAttribute(S.attrUser, user);
            request.setAttribute(S.attrWins, wins);
            request.setAttribute(S.attrLoses, loses);
            Jsp.forward(request, response, S.jspProfile);
        } else Jsp.redirect(request, response, Go.ROOT);


    }
}