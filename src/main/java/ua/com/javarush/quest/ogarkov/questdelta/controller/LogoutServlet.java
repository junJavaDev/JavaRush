package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.*;

@WebServlet(LOGOUT)
public class LogoutServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -7331193555895053081L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        var oldLocale = Optional.ofNullable(session.getAttribute("locale"));
        String redirectURI = LOGIN;
        session.invalidate();
        if (oldLocale.isPresent()) {
            redirectURI = redirectURI + "?locale=" + oldLocale.get();
        }
        Jsp.redirect(resp, redirectURI);
    }
}
