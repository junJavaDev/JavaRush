package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "editQuest", value = EDIT_QUEST)
public class EditQuestServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = ReqParser.getLong(req, "id");
        Optional<Quest> optQuest = questService.get(id);
        optQuest.ifPresent(quest -> req.setAttribute("quest", quest));
        Jsp.forward(req, resp, "/quest/editQuest");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        long id = ReqParser.getLong(req, "id");
        boolean present = questService.get(id).isPresent();
        if (present && req.getParameter("update") != null) {
            questService.update(req);
        } else if (!present && req.getParameter("create") != null) {
            Quest quest = questService.create(req);
            id = quest.getId();
        }
        Jsp.redirect(req, resp, EDIT_QUEST_CONTENT + "?id=" + id);

    }
}
