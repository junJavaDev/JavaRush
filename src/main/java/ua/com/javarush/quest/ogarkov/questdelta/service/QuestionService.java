package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public enum QuestionService {

    INSTANCE;
    private static final Setting S = Setting.get();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final ImageService imageService = ImageService.INSTANCE;

    public Optional<Question> get(long id) {
        return questionRepository.get(id);
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    public Question create(HttpServletRequest req) throws ServletException, IOException {
        Long id = ReqParser.getLong(req, S.paramId);
        Quest quest = questRepository.get(id).orElseThrow();

        GameState gameState = GameState.valueOf(req.getParameter(S.inputGameState));
        String name = req.getParameter(S.inputName);
        String text = req.getParameter(S.inputText);

        Question question = Question.with()
                .questId(quest.getId())
                .gameState(gameState)
                .name(name)
                .text(text)
                .build();
        questionRepository.create(question);

        quest.getQuestions().add(question);
        return question;
    }

    public Question createEmpty(Quest quest) {
        Question question = Question.with()
                .questId(quest.getId())
                .gameState(GameState.PLAY)
                .build();
        questionRepository.create(question);
        quest.getQuestions().add(question);
        return question;
    }

    public void delete(Question question) {
        Quest quest = questRepository
                .get(question.getQuestId())
                .orElseThrow();
        for (Answer answer : question.getAnswers()) {
            answerRepository.delete(answer);
        }

        Collection<Answer> answers = answerRepository.find(
                Answer.with()
                        .nextQuestionId(question.getId())
                        .build());
        for (Answer answer : answers) {
            Optional<Question> questionWithAnswer = get(answer.getQuestionId());
            questionWithAnswer.ifPresent(
                    value -> value.getAnswers().remove(answer)
            );
            answerRepository.delete(answer);
        }
        if (question.getImage() != null) {
            imageService.deleteImage(question.getImage());
        }
        quest.getQuestions().remove(question);
        questionRepository.delete(question);
    }


    public void update(HttpServletRequest req) {
        Long questId = ReqParser.getLong(req, S.paramId);
        Quest quest = questRepository
                .get(questId)
                .orElseThrow();
        long questionIndex = ReqParser.getLong(req, S.paramQuestionIndex);
        Question question = quest
                .getQuestions()
                .get((int) questionIndex);

        String name = req.getParameter(S.inputName);
        String text = req.getParameter(S.inputText);
        GameState gameState = GameState.valueOf(req.getParameter(S.inputGameState));
        question.setName(name);
        question.setText(text);
        question.setGameState(gameState);

        try {
            Part data = req.getPart(S.inputImage);
            String image = S.questsDir
                    + questId + "/"
                    + question.getId()
                    + ReqParser.getFileExtension(data.getSubmittedFileName());
            boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
            if (isUploaded) {
                question.setImage(image);
            }
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }

}
