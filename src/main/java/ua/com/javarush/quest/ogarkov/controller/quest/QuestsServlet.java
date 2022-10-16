package ua.com.javarush.quest.ogarkov.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.GameDto;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.service.PaginationService;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.io.Serial;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@WebServlet({Go.QUESTS, Go.EDIT_QUESTS})
public class QuestsServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -776002015199337931L;
    private final PaginationService paginationService = PaginationService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        paginationService
                .getQuestsPagination(FormData.of(req))
                .fillRequest(req);
        Set<Long> openQuests = getOpenQuests(req);
        req.setAttribute(S.attrOpenQuests, openQuests);
        if (Parser.getCommand(req).equals(Go.QUESTS)) {
            Jsp.forward(req, resp, S.jspQuests);
        } else {
            Jsp.forward(req, resp, S.jspEditQuests);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private Set<Long> getOpenQuests(HttpServletRequest req) {
        long userId = Parser.userId(req);
        Optional<UserDto> optUser = userService.get(userId);
        Set<Long> openQuests = new HashSet<>();
        optUser.ifPresent(user -> {
            if (user.getGames() != null) {
                for (GameDto game : user.getGames()) {
                    openQuests.add(game.getQuestId());
                }
            }
        });
        return openQuests;
    }
}