package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@WebServlet(Go.LOGOUT)
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -7331193555895053081L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        var oldLocale = Optional.ofNullable(session.getAttribute("lang"));
        String redirectURI = Go.LOGIN;
        session.invalidate();
        if (oldLocale.isPresent()) {
            redirectURI = redirectURI + "?lang=" + oldLocale.get();
        }
        Jsp.redirect(req, resp, redirectURI);
    }
}
