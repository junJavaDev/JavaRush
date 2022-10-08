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
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;


@WebServlet(name = "profileServlet", value = Go.PROFILE)
public class ProfileServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 856906541196618136L;
    UserService userService = UserService.INSTANCE;
    GameSessionService gameSessionService = GameSessionService.INSTANCE;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long id = ReqParser.getLong(request, "id");
        Optional<User> optUser = userService.get(id);

        if (optUser.isPresent()) {
            User user = optUser.get();
            int wins = gameSessionService.getAll(user.getId(), GameState.WIN).size();
            int loses = gameSessionService.getAll(user.getId(), GameState.LOSE).size();
            request.setAttribute("user", user);
            request.setAttribute("wins", wins);
            request.setAttribute("loses", loses);
            Jsp.forward(request, response, "/user/profile");
        } else Jsp.redirect(request, response, Go.ROOT);


    }
}