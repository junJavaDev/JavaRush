package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@WebServlet(Go.LOGOUT)
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -7331193555895053081L;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        var oldLocale = Optional.ofNullable(session.getAttribute(S.attrLang));
        String redirectURI = Go.LOGIN;
        session.invalidate();
        if (oldLocale.isPresent()) {
            redirectURI = redirectURI + "?" + S.paramLang + "=" + oldLocale.get();
        }
        Jsp.redirect(req, resp, redirectURI);
    }
}
