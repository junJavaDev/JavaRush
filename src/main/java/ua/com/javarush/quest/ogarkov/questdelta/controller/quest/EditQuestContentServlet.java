package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.service.AnswerService;
import ua.com.javarush.quest.ogarkov.questdelta.service.EditorService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestService;
import ua.com.javarush.quest.ogarkov.questdelta.service.QuestionService;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        User user = ReqParser.getUser(req).orElseThrow();
        Optional<Quest> optQuest = questService.get(ReqParser.getId(req));
        if (optQuest.isPresent() && editorService.checkRights(optQuest.get(), user)) {
            Quest quest = optQuest.get();
            long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
            List<Question> questions = quest.getQuestions();
            Question question = questions.get((int) questionIndex);
            req.setAttribute(S.attrQuest, quest);
            req.setAttribute(S.attrQuestion, question);
            req.setAttribute(S.attrAnswers, getAnswers(question));
            Jsp.forward(req, resp, S.jspEditQuestContent);
        } else Jsp.redirect(req, resp, Go.QUESTS);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long questId = ReqParser.getLong(req, S.paramId);
        Optional<Quest> optQuest = questService.get(questId);
        if (optQuest.isPresent()) {
            Quest quest = optQuest.get();
            long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
            //----------- Create question -----------//
            if (isExist(req, S.paramQuestionCreate)) {
                questionService.createEmpty(quest);
                questionIndex = quest.getQuestions().size() - 1;
                Jsp.redirect(req, resp, editorService.getEditPath(questId, questionIndex));
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
            Jsp.redirect(req, resp, editorService.getEditPath(questId, questionIndex));
        } else Jsp.redirect(req, resp, Go.QUESTS);
    }

    private boolean isExist(HttpServletRequest req, String param) {
        return req.getParameter(param) != null;
    }

    private List<Map.Entry<Answer, Question>> getAnswers(Question question) {
        List<Map.Entry<Answer, Question>> answers = new ArrayList<>();
        for (Answer answer : question.getAnswers()) {
            Question nextQuestion = questionService
                    .get(answer.getNextQuestionId())
                    .orElseThrow();
            answers.add(Map.entry(answer, nextQuestion));
        }
        return answers;
    }
}
