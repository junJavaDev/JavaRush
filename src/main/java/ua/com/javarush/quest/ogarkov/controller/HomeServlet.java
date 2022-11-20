package ua.com.javarush.quest.ogarkov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;

@WebServlet(value = Go.HOME)
public class HomeServlet extends HttpServlet {

    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(HomeServlet.class);

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.info("Open page: {}, userID: {}", Go.HOME, Parser.userId(req));
        Jsp.forward(req, resp, S.jspHome);
    }
}