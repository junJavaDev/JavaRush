package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;

import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.ABOUT;
import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.PROFILE;

@WebServlet(name = "profileServlet", value = PROFILE)
public class ProfileServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 856906541196618136L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Jsp.forward(request, response, PROFILE);
    }
}