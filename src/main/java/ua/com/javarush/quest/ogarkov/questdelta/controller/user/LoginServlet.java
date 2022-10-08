package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@WebServlet(Go.LOGIN)
public class LoginServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8839039694577428788L;

    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "/user/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> optionalUser = userService
                .find(User.with()
                        .login(login)
                        .password(password)
                        .build())
                .stream()
                .findFirst();
        if (optionalUser.isPresent()) {
            HttpSession session = req.getSession();
            User user = optionalUser.get();
            session.setAttribute("user", user); //must be UserDTO
            session.setAttribute("userId", user.getId()); //must be UserDTO
            session.setAttribute("lang", user.getLanguage().name());
            String uri = Go.PROFILE + "?id=" + user.getId();
            Jsp.redirect(req, resp, uri);

        } else {
            req.setAttribute("error", "User not found");
            Jsp.redirect(req, resp, Go.ROOT);

//            Jsp.forward(req, resp, "/user/login");
        }
    }
}
