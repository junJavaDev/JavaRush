package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.LOGOUT)
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -7331193555895053081L;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(LogoutServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Open page: {}, userID: {}", Go.LOGOUT, Parser.userId(req));
        HttpSession session = req.getSession();
        var oldLocale = Optional.ofNullable(session.getAttribute(S.attrLang));
        String login = userService
                .get(Parser.userId(req))
                .orElseThrow()
                .getLogin();
        String redirectURI = Go.LOGIN;
        session.invalidate();
        log.info("{} is successfully logged out", login);
        if (oldLocale.isPresent()) {
            redirectURI = redirectURI + "?" + S.paramLang + "=" + oldLocale.get();
        }
        Jsp.redirect(req, resp, redirectURI);
    }
}
