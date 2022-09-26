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
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.util.Setting.*;

@WebServlet(name = "UsersServlet", value = {USERS})
public class UsersServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8048051397301683584L;
    UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> pageNumberOpt = Optional.ofNullable(request.getParameter("pageNumber"));
        Optional<String> pageSizeOpt = Optional.ofNullable(request.getParameter("pageSize"));
        int usersCount = userService.getAll().size();

        int pageNumber = pageNumberOpt
                .map(Integer::parseInt)
                .orElse(1);

        int pageSize = pageSizeOpt
                .map(Integer::parseInt)
                .orElse(DEFAULT_PAGE_SIZE);


        int pages = usersCount / pageSize;
        if (usersCount % pageSize > 0) {
            pages++;
        }

        if (pages < pageNumber) {
            pageNumber = 1;
        }
        Collection<User> users = userService.getAll(pageNumber, pageSize);

        request.setAttribute("pages", pages);
        request.setAttribute("activePage", pageNumber);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("users", users);
        Jsp.forward(request, response, USERS);
    }
}
