package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.questdelta.dto.UserDto;
import ua.com.javarush.quest.ogarkov.questdelta.service.EditorService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.UserService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.Parser;

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
    private final UserService userService = UserService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long userId = Parser.userId(req);
        UserDto user = userService.get(userId).orElseThrow();
        Optional<QuestDto> optQuest = questService.get(formData.getId());
        if (optQuest.isPresent() && editorService.checkRights(optQuest.get(), user)) {
            QuestDto quest = optQuest.get();
            req.setAttribute(S.attrQuest, quest);
        }
        Jsp.forward(req, resp, S.jspEditQuest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        long id = formData.getId();
        boolean present = questService.get(id).isPresent();
        if (present && req.getParameter(S.inputUpdate) != null) {
            questService.update(formData, req.getPart(S.inputImage), req.getPart(S.inputTwine));
        } else if (!present && req.getParameter(S.inputCreate) != null) {
            long authorId = Parser.userId(req);
            QuestDto quest = questService.create(formData, req.getPart(S.inputImage), req.getPart(S.inputTwine), authorId);
            id = quest.getId();
        }
        Jsp.redirect(req, resp, editorService.getEditPath(id));
    }
}
