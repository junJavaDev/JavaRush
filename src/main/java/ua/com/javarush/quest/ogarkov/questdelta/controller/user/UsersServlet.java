package ua.com.javarush.quest.ogarkov.questdelta.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.service.PaginationService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;

import java.io.IOException;
import java.io.Serial;

@WebServlet(Go.USERS)
public class UsersServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8048051397301683584L;
    private final UserService userService = UserService.INSTANCE;
    private final PaginationService paginationService = PaginationService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paginationService
                .getUsersPagination(FormData.of(req))
                .fillRequest(req);
        Jsp.forward(req, resp, S.jspEditUsers);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String delete = req.getParameter(S.inputDelete);
        if (delete != null) {
            long id = Long.parseLong(delete);
            userService.delete(id);
        }
        Jsp.redirect(req, resp, Go.USERS);
    }
}
