package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.EditorService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.EDIT_QUEST)
public class EditQuestServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final EditorService editorService = EditorService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = ReqParser.getUser(req).orElseThrow();
        Optional<Quest> optQuest = questService.get(ReqParser.getId(req));
        if (optQuest.isPresent() && editorService.checkRights(optQuest.get(), user)) {
            Quest quest = optQuest.get();
            req.setAttribute(S.attrQuest, quest);
        }
        Jsp.forward(req, resp, S.jspEditQuest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id = ReqParser.getLong(req, S.paramId);
        boolean present = questService.get(id).isPresent();
        if (present && req.getParameter(S.inputUpdate) != null) {
            questService.update(req);
        } else if (!present && req.getParameter(S.inputCreate) != null) {
            Quest quest = questService.create(req);
            id = quest.getId();
        }
        Jsp.redirect(req, resp, editorService.getEditPath(id));
    }
}
