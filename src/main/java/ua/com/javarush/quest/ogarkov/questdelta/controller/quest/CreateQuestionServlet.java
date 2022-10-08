package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.service.EditorService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.CREATE_QUESTION)
public class CreateQuestionServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = -1841848703475154198L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final EditorService editorService = EditorService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, S.jspCreateQuestion);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = ReqParser.getLong(req, S.paramId);

        Optional<Quest> optQuest = questService.get(id);
        if (optQuest.isPresent()) {
            Question question = questionService.create(req);
            Jsp.redirect(req, resp, Go.EDIT_QUEST_CONTENT + editorService.getEditPath(question));
        } else Jsp.redirect(req, resp, Go.QUESTS);
    }
}
