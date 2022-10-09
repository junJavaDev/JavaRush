package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.DataTank;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.PaginationService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@WebServlet({Go.QUESTS, Go.EDIT_QUESTS})
public class QuestsServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -776002015199337931L;
    private final PaginationService paginationService = PaginationService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataTank formData = DataTank.of(req);
        paginationService
                .getQuestsPagination(formData)
                .fillRequest(req);
        Set<Long> openQuests = getOpenQuests(req);
        req.setAttribute(S.attrOpenQuests, openQuests);
        if (ReqParser.getCommand(req).equals(Go.QUESTS)) {
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
        Optional<User> optUser = ReqParser.getUser(req);
        Set<Long> openQuests = new HashSet<>();
        optUser.ifPresent(user -> {
            Collection<GameSession> gameSessions = user.getGameSessions();
            for (GameSession gameSession : gameSessions) {
                openQuests.add(gameSession.getQuestId());
            }
        });
        return openQuests;
    }
}