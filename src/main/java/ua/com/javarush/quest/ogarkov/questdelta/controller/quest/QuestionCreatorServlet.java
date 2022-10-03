package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.List;
import java.util.Optional;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "questionCreator", value = QUESTION_CREATE)
public class QuestionCreatorServlet extends HttpServlet {


    @Serial
    private static final long serialVersionUID = -1841848703475154198L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute("gameStates", GameState.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, "/quest/createQuestion");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long id = ReqParser.getId(req, "id");

        Optional<Quest> optQuest = questService.get(id);
        if (optQuest.isPresent()) {
            Quest quest = optQuest.get();

            GameState gameState = GameState.valueOf(req.getParameter("gameState"));
            String name = req.getParameter("name");
            String text = req.getParameter("text");
            Part data = req.getPart("image");


            Question question = Question.with()
                    .gameState(gameState)
                    .name(name)
                    .text(text)
                    .build();
            questionService.create(question);

            List<Question> questions = quest.getQuestions();
            int questionIndex = questions.size();
            questions.add(question);

            Long questId = quest.getId();

            String image = "quests/" + questId + "/" + question.getId() + ReqParser.getFileExtension(data.getSubmittedFileName());
            boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
            if (isUploaded) {
                question.setImage(image);
            }
            Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + questionIndex);
        } else Jsp.redirect(req, resp, QUESTS);

    }
}
