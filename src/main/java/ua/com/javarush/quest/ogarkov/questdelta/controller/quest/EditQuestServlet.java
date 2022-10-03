package ua.com.javarush.quest.ogarkov.questdelta.controller.quest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.service.*;
import ua.com.javarush.quest.ogarkov.questdelta.util.Jsp;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.io.Serial;
import java.util.*;

import static ua.com.javarush.quest.ogarkov.questdelta.settings.Default.*;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(name = "questEdit", value = QUEST_EDIT)
public class EditQuestServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 7582798421846485830L;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final AnswerService answerService = AnswerService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;
    private final UserService userService = UserService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute("gameStates", GameState.values());
    }

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
                Question question = questions.get((int) questionIndex);
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
                Jsp.forward(req, resp, "/quest/editQuest");
            }
        } else Jsp.redirect(req, resp, LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long questId = ReqParser.getId(req, "id");
        Optional<Quest> optQuest = questService.get(questId);
        if (optQuest.isPresent()) {
            Quest quest = optQuest.get();

            String questionDeleteParam = req.getParameter("questionDelete");
            String questionCreateParam = req.getParameter("questionCreate");
            String questionUpdateParam = req.getParameter("questionUpdate");
            String answerCreateParam = req.getParameter("answerCreate");
            String answerDeleteParam = req.getParameter("answerDelete");
            String questDeleteParam = req.getParameter("questDelete");
            long questionIndex = ReqParser.getId(req, "questionIndex");

            //------------------- Удаление вопроса ----------------------//
            if (questionDeleteParam != null) {
                long questionToDeleteId = Long.parseLong(questionDeleteParam);
                Optional<Question> optQuestion = questionService.get(questionToDeleteId);
                if (optQuestion.isPresent()) {
                    Question question = optQuestion.get();
                    if (!Objects.equals(quest.getFirstQuestionId(), question.getId())) {
                        questionDelete(quest, question);
                    }
                }
                //------------------- Удаление вопроса ----------------------//
                //------------------- Создание вопроса ----------------------//
            } else if (questionCreateParam != null) {
                Question question = Question.with().gameState(GameState.PLAY).build();
                questionService.create(question);
                quest.getQuestions().add(question);
                Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + (quest.getQuestions().size() - 1));
                return;
                //------------------- Создание вопроса ----------------------//
                //------------------- Создание ответа ----------------------//
            } else if (answerCreateParam != null) {
                String answerText = req.getParameter("answer");
                Long nextQuestionId = Long.parseLong(req.getParameter("nextQuestionId"));

                Question question = quest.getQuestions().get((int) questionIndex);
                Answer answer = Answer.with()
                        .questionId(question.getId())
                        .nextQuestionId(nextQuestionId)
                        .text(answerText)
                        .build();
                answerService.create(answer);
                question.getAnswers().add(answer);
                Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + questionIndex);
                return;
            } else if (answerDeleteParam != null) {
                long answerId = Long.parseLong(answerDeleteParam);
                Optional<Answer> optAnswer = answerService.get(answerId);
                if (optAnswer.isPresent()) {
                    Answer answer = optAnswer.get();
                    Optional<Question> optQuestion = questionService.get(answer.getQuestionId());
                    if (optQuestion.isPresent()) {
                        Question question = optQuestion.get();
                        question.getAnswers().remove(answer);
                    }
                    answerService.delete(answer);
                }
                Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + questionIndex);
                return;
            } else if (questionUpdateParam != null) {
                String name = req.getParameter("name");
                String text = req.getParameter("text");
                GameState gameState = GameState.valueOf(req.getParameter("gameState"));
                Part data = req.getPart("image");
                Question question = quest.getQuestions().get((int) questionIndex);
                question.setName(name);
                question.setText(text);
                question.setGameState(gameState);
                String image = "quests/" + questId + "/" + question.getId() + ReqParser.getFileExtension(data.getSubmittedFileName());
                boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
                if (isUploaded) {
                    question.setImage(image);
                }
                Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + questionIndex);
                return;
            } else if (questDeleteParam != null) {
                long questDeleteId = Long.parseLong(questDeleteParam);
                Optional<Quest> optQuestDelete = questService.get(questDeleteId);
                if (optQuestDelete.isPresent()) {
                    Quest questDelete = optQuestDelete.get();
                    for (int qIndex = 0; qIndex < questDelete.getQuestions().size(); qIndex++) {
                        questionDelete(questDelete, questDelete.getQuestions().get(qIndex));
                    }
                    if (questDelete.getImage() != null) {
                        imageService.deleteImage(questDelete.getImage());
                    }
                    Optional<User> optAuthor = userService.get(questDelete.getAuthorId());
                    if (optAuthor.isPresent()) {
                        User author = optAuthor.get();
                        author.getQuests().remove(questDelete);
                    }
                    questService.delete(questDelete);
                }
                Jsp.redirect(req, resp, QUESTS_EDIT);
                return;
            }
            Jsp.redirect(req, resp, QUEST_EDIT + "?id=" + questId + "&" + PARAM_QUESTION_INDEX + "=" + questionIndex);
        }
    }

    private void questionDelete(Quest quest, Question question) {
        for (Answer answer : question.getAnswers()) {
            answerService.delete(answer);
        }

        Collection<Answer> answers = answerService.find(Answer.with().nextQuestionId(question.getId()).build());
        for (Answer answer : answers) {
            Optional<Question> questionWithAnswer = questionService.get(answer.getQuestionId());
            questionWithAnswer.ifPresent(value -> value.getAnswers().remove(answer));
            answerService.delete(answer);
        }
        if (question.getImage() != null) {
            imageService.deleteImage(question.getImage());
        }
        quest.getQuestions().remove(question);
        questionService.delete(question);
    }

}
