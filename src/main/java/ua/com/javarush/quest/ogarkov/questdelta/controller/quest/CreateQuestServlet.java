package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.QUEST_CREATE;
import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.QUEST_EDIT;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "questCreate", value=QUEST_CREATE)
public class CreateQuestServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "/quest/createQuest");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = ReqParser.getUser(req).orElseThrow();
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        Part data = req.getPart("image");


        Question firstQuestion = Question.with().gameState(GameState.PLAY).build();
        questionService.create(firstQuestion);

        Quest quest = Quest.with()
                .authorId(user.getId())
                .firstQuestionId(firstQuestion.getId())
                .name(name)
                .text(text)
                .build();

        quest.getQuestions().add(firstQuestion);
        questService.create(quest);
        user.getQuests().add(quest);

        Long questId = quest.getId();
        String image = "quests/" + questId + "/quest_image" + ReqParser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
        if (isUploaded) {
            quest.setImage(image);
        }


        Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId);

    }
}
