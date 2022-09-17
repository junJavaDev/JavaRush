package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Collection;

import static ua.com.javarush.quest.ogarkov.questdelta.util.UriString.*;

@WebServlet(name = "UsersServlet", value = {USERS})
public class UsersServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8048051397301683584L;
    UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<User> users = userService.getAll();
        request.setAttribute("users", users);
        Jsp.forward(request, response, USERS);
    }
}
