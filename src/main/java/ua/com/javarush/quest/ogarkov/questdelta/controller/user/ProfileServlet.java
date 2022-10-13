package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.UserDto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.service.GameService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(value = Go.PROFILE)
public class ProfileServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 856906541196618136L;
    private final UserService userService = UserService.INSTANCE;
    private final GameService gameService = GameService.INSTANCE;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        long userId = formData.getId();
        Optional<UserDto> optUser = userService.get(userId);
        if (optUser.isPresent()) {
            UserDto user = optUser.get();
            int wins = gameService.getAll(user.getId(), GameState.WIN).size();
            int loses = gameService.getAll(user.getId(), GameState.LOSE).size();
            req.setAttribute(S.attrUser, user);
            req.setAttribute(S.attrWins, wins);
            req.setAttribute(S.attrLoses, loses);
            Jsp.forward(req, resp, S.jspProfile);
        } else Jsp.redirect(req, resp, Go.ROOT);


    }
}