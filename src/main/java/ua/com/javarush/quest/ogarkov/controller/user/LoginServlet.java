package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.service.PasswordService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.LOGIN)
public class LoginServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -8839039694577428788L;
    private final PasswordService passwordService = PasswordService.INSTANCE;
    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Open page: {}, userID: {}", Go.LOGIN, Parser.userId(req));
        Jsp.forward(req, resp, S.jspLogin);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String login = req.getParameter(S.inputLogin);
        String password = req.getParameter(S.inputPassword);
        Optional<UserDto> optionalUser = passwordService.check(login, password);
        if (optionalUser.isPresent()) {
            HttpSession session = req.getSession();
            UserDto user = optionalUser.get();
            log.info("{} is successfully logged in", user.getLogin());
            session.setAttribute(S.attrUser, user);
            session.setAttribute(S.attrUserId, user.getId());
            session.setAttribute(S.attrLang, user.getLanguage().name());
            String uri = Go.PROFILE + "?" + S.paramId + "=" + user.getId();
            Jsp.redirect(req, resp, uri);
        } else {
            log.warn("Wrong login/password, login: {}", login);
            Jsp.redirect(req, resp, Go.LOGIN);
        }
    }
}
