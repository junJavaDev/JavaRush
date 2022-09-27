package ua.com.javarush.quest.ogarkov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.service.ImageService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "questEditor", value="/quest-edit")
public class QuestEditor extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(req);
        Long questId = ReqParser.getId(req, "id");
        Optional<Quest> optQuest = questService.get(questId);
        if (optUser.isPresent() && optQuest.isPresent()) {
            User user = optUser.get();
            Quest quest = optQuest.get();

            if (Objects.equals(user.getId(), quest.getAuthorId()) || user.getRole() == Role.ADMIN) {
                long questionIndex = ReqParser.getId(req, "questionIndex");
                List<Question> questions = quest.getQuestions();
                Question question = questions.get((int)questionIndex);
                List<Map.Entry<Answer, Question>> answers = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    Question nextQuestion = questionService.get(answer.getNextQuestionId()).orElseThrow();
//                    int nextQuestionIndex = questions.indexOf(nextQuestion);
//                    String nextQuestionCountPrefix = (1 + nextQuestionIndex) + " ";
                    answers.add(Map.entry(answer, nextQuestion));
                }

                req.setAttribute("quest", quest);
                req.setAttribute("question", question);
                req.setAttribute("answers", answers);
                Jsp.forward(req, resp, "/questEditor");
            }
        } else Jsp.redirect(resp, "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = ReqParser.getUser(req).orElseThrow();
        String name = req.getParameter("name");
        String text = req.getParameter("text");
        Part data = req.getPart("image");


        Question firstQuestion = Question.with().build();
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


        Jsp.redirect(resp, "/questEditor");

    }
}
