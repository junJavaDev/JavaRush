package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.Dto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.PaginationService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

@WebServlet({Go.QUESTS, Go.EDIT_QUESTS})
public class QuestsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -776002015199337931L;
    private final QuestService questService = QuestService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;
    private final PaginationService paginationService = PaginationService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dto dto = Dto.of(req);

        int pageNumber = paginationService.getPageNumber(dto);
        int pageSize = paginationService.getPageSize(dto);
        int pageCount = paginationService.getPageCount(dto);
        Set<Long> openQuests = getOpenQuests(req);
        Collection<Quest> quests = questService.getAll(pageNumber, pageSize);
        Map<Long, String> authors = getAuthors(quests);

        req.setAttribute(S.attrAuthors, authors);
        req.setAttribute(S.attrQuests, quests);
        req.setAttribute(S.attrOpenQuests, openQuests);
        req.setAttribute(S.attrPageNumber, pageNumber);
        req.setAttribute(S.attrPageSize, pageSize);
        req.setAttribute(S.attrPageCount, pageCount);

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

    private Map<Long, String> getAuthors(Collection<Quest> quests) {
        Map<Long, String> authors = new HashMap<>();
        for (Quest quest : quests) {
            String userName = S.deleted;
            Optional<User> user = userService.get(quest.getAuthorId());
            if (user.isPresent()) {
                userName = user.get().getLogin();
            }
            authors.put(quest.getId(), userName);
        }
        return authors;
    }
}