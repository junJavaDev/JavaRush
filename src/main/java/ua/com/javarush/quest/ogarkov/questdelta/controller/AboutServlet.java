package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;

@WebServlet(Go.ABOUT)
public class AboutServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6316268318161580029L;
    private final Setting S = Setting.get();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Jsp.forward(request, response, S.jspAbout);
    }
}