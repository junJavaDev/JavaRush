package ua.com.javarush.quest.ogarkov.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.service.EditorService;
import ua.com.javarush.quest.ogarkov.service.QuestService;
import ua.com.javarush.quest.ogarkov.service.UserService;
import ua.com.javarush.quest.ogarkov.settings.Go;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Jsp;
import ua.com.javarush.quest.ogarkov.util.Parser;

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
    private static final Logger log = LoggerFactory.getLogger(EditQuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FormData formData = FormData.of(req);
        long userId = Parser.userId(req);
        log.info("Open page: {}, userID: {}", Go.EDIT_QUEST, userId);
        UserDto user = userService.get(userId).orElseThrow();
        Optional<QuestDto> optQuest = questService.get(formData.getId());
        if (optQuest.isPresent() && editorService.checkRights(optQuest.get(), user)) {
            QuestDto quest = optQuest.get();
            log.info("Load quest to edit, quest: {}, user: {}", quest, user);
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
            log.info("Quest created: {}", quest);
        }
        Jsp.redirect(req, resp, editorService.getEditPath(id));
    }
}
