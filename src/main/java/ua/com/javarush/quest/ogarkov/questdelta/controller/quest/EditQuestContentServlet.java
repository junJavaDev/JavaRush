package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.service.*;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.EDIT_QUEST_CONTENT)
public class EditQuestContentServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final EditorService editorService = EditorService.INSTANCE;
    private final Setting S = Setting.get();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> optUser = ReqParser.getUser(req);
        Long questId = ReqParser.getLong(req, S.paramId);
        Optional<Quest> optQuest = questService.get(questId);
        if (optUser.isPresent() && optQuest.isPresent()) {
            User user = optUser.get();
            Quest quest = optQuest.get();

            if (Objects.equals(user.getId(), quest.getAuthorId()) || user.getRole() == Role.ADMIN) {
                long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
                List<Question> questions = quest.getQuestions();
                Question question = questions.get((int) questionIndex);
                List<Map.Entry<Answer, Question>> answers = new ArrayList<>();
                for (Answer answer : question.getAnswers()) {
                    Question nextQuestion = questionService
                            .get(answer.getNextQuestionId())
                            .orElseThrow();
                    answers.add(Map.entry(answer, nextQuestion));
                }

                req.setAttribute(S.attrQuest, quest);
                req.setAttribute(S.attrQuestion, question);
                req.setAttribute(S.attrAnswers, answers);
                Jsp.forward(req, resp, S.jspEditQuestContent);
            }
        } else Jsp.redirect(req, resp, Go.QUESTS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long questId = ReqParser.getLong(req, S.paramId);
        Optional<Quest> optQuest = questService.get(questId);
        if (optQuest.isPresent()) {
            Quest quest = optQuest.get();
            long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
            Question question = quest.getQuestions().get((int) questionIndex);

            //----------- Create question -----------//
            if (isExist(req, S.paramQuestionCreate)) {
                question = questionService.createEmpty(quest);
                Jsp.redirect(req, resp, editorService.getEditPath(question));
                return;

                //----------- Update question -----------//
            } else if (isExist(req, S.paramQuestionUpdate)) {
                questionService.update(req);

                //----------- Delete question -----------//
            } else if (isExist(req, S.paramQuestionDelete)) {
                long id = ReqParser.getLong(req, S.paramQuestionDelete);
                Optional<Question> optQuestion = questionService.get(id);
                optQuestion.ifPresent(editorService::deleteNonFirstQuestion);

                //----------- Create answer -----------//
            } else if (isExist(req, S.paramAnswerCreate)) {
                answerService.create(req);

                //----------- Delete answer -----------//
            } else if (isExist(req, S.paramAnswerDelete)) {
                answerService.delete(req);

                //----------- Delete quest -----------//
            } else if (isExist(req, S.paramQuestDelete)) {
                questService.delete(req);
                Jsp.redirect(req, resp, Go.EDIT_QUESTS);
                return;
            }
            Jsp.redirect(req, resp, editorService.getEditPath(question));
        } else Jsp.redirect(req, resp, Go.QUESTS);
    }

    private boolean isExist(HttpServletRequest req, String param) {
        return req.getParameter(param) != null;
    }
}
