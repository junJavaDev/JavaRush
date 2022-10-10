package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.util.Collection;
import java.util.Optional;

public enum AnswerService {

    INSTANCE;
    private final Setting S = Setting.get();
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<Quest> questRepository = QuestRepository.getInstance();

    public Optional<Answer> get(long id) {
        return answerRepository.get(id);
    }

    public Collection<Answer> getAll() {
        return answerRepository.getAll();
    }

    public void create(HttpServletRequest req) {
        Long questId = ReqParser.getLong(req, S.paramId);
        Quest quest = questRepository
                .get(questId)
                .orElseThrow();
        long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
        Question question = quest
                .getQuestions()
                .get((int) questionIndex);

        String answerText = req.getParameter(S.inputAnswer);
        Long nextQuestionId = ReqParser.getLong(req, S.paramNextQuestionId);

        Answer answer = Answer.with()
                .questionId(question.getId())
                .nextQuestionId(nextQuestionId)
                .text(answerText)
                .build();

        answerRepository.create(answer);
        question.getAnswers().add(answer);
    }

    public void delete(HttpServletRequest req) {
        long answerId = ReqParser.getLong(req, S.paramAnswerDelete);
        Optional<Answer> optAnswer = answerRepository.get(answerId);
        if (optAnswer.isPresent()) {
            Answer answer = optAnswer.get();
            Optional<Question> optQuestion = questionRepository.get(answer.getQuestionId());
            optQuestion.ifPresent(question -> question.getAnswers().remove(answer));
            answerRepository.delete(answer);
        }
    }
}
