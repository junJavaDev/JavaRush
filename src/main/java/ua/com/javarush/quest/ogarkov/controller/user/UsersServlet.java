package ua.com.javarush.quest.ogarkov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.service.PaginationService;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;

@WebServlet(Go.USERS)
public class UsersServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -8048051397301683584L;
    private final UserService userService = UserService.INSTANCE;
    private final PaginationService paginationService = PaginationService.INSTANCE;
    private final Setting S = Setting.get();
    private static final Logger log = LoggerFactory.getLogger(ProfileServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Open page: {}, userID: {}", Go.USERS, Parser.userId(req));
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
