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
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.LOGIN)
public class LoginServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -8839039694577428788L;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, S.jspLogin);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(S.inputLogin);
        String password = req.getParameter(S.inputPassword);
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
            session.setAttribute(S.attrUser, user);
            session.setAttribute(S.attrUserId, user.getId());
            session.setAttribute(S.attrLang, user.getLanguage().name());
            String uri = Go.PROFILE + "?" + S.paramId + "=" + user.getId();
            Jsp.redirect(req, resp, uri);
        } else {
            Jsp.redirect(req, resp, Go.ROOT);
        }
    }
}
