package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.entity.GameState;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.service.GameService;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;

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
    private static final Logger log = LoggerFactory.getLogger(ProfileServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        long userId = formData.getId();
        log.info("Open page: {}, userID: {}", Go.PROFILE, userId);
        Optional<UserDto> optUser = userService.get(userId);
        if (optUser.isPresent()) {
            UserDto user = optUser.get();
            int wins = gameService.getAll(user.getId(), GameState.WIN).size();
            int loses = gameService.getAll(user.getId(), GameState.LOSE).size();
            req.setAttribute(S.attrUser, user);
            req.setAttribute(S.attrWins, wins);
            req.setAttribute(S.attrLoses, loses);
            Jsp.forward(req, resp, S.jspProfile);
        } else {
            log.warn("Profile with id {} is not exist", userId);
            Jsp.redirect(req, resp, Go.ROOT);
        }


    }
}