package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.QUESTS;
import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.QUESTS_EDIT;

@WebServlet(name = "questsServlet", value = {QUESTS, QUESTS_EDIT})
public class QuestsServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = -776002015199337931L;
    QuestService questService = QuestService.INSTANCE;
    UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<String> pageNumberOpt = Optional.ofNullable(request.getParameter("pageNumber"));
        Optional<String> pageSizeOpt = Optional.ofNullable(request.getParameter("pageSize"));
        int usersCount = questService.getAll().size();

        int pageNumber = pageNumberOpt
                .map(Integer::parseInt)
                .orElse(1);

        int pageSize = pageSizeOpt
                .map(Integer::parseInt)
                .orElse(9);


        int pages = usersCount / pageSize;
        if (usersCount % pageSize > 0) {
            pages++;
        }

        if (pages < pageNumber) {
            pageNumber = 1;
        }

        Optional<User> optUser = ReqParser.getUser(request);
        if (optUser.isPresent()) {
            User user = optUser.get();
            Collection<GameSession> gameSessions = user.getGameSessions();

            Set<Long> openQuests = new HashSet<>();
            for (GameSession gameSession : gameSessions) {
                openQuests.add(gameSession.getQuestId());
            }
            request.setAttribute("openQuests", openQuests);
        }

        Collection<Quest> quests = questService.getAll(pageNumber, pageSize);
        Map<Long, String> authors = new HashMap<>();
        for (Quest quest : quests) {
            String userName = "Deleted";
            Optional<User> user = userService.get(quest.getAuthorId());
            if (user.isPresent()) {
                userName = user.get().getLogin();
            }
            authors.put(quest.getId(), userName);
        }
        request.setAttribute("authors", authors);
        request.setAttribute("pages", pages);
        request.setAttribute("activePage", pageNumber);
        request.setAttribute("pageSize", pageSize);
        request.setAttribute("quests", quests);
        if (request.getRequestURI().endsWith("/quests")) {
            Jsp.forward(request, response, "/quest/quests");
        } else {
            Jsp.forward(request, response, "/quest/editQuests");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}