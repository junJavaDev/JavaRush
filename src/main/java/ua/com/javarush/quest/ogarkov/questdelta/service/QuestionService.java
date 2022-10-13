package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.QuestionDto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameState;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.Parser;

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

    public Optional<QuestionDto> get(long id) {
        Question question = questionRepository.get(id);
        return Mapper.question.dtoOf(question);
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    public Question createEmpty(long questId) {
        Quest quest = questRepository.get(questId);
        Question question = Question.with()
                .questId(questId)
                .gameState(GameState.PLAY)
                .build();
        questionRepository.create(question);
        quest.getQuestions().add(question);
        return question;
    }

    public void delete(long id) {
        Question question = questionRepository.get(id);
        Quest quest = questRepository.get(question.getQuestId());
        for (Answer answer : question.getAnswers()) {
            answerRepository.delete(answer);
        }

        Collection<Answer> answers = answerRepository.find(
                Answer.with()
                        .nextQuestionId(question.getId())
                        .build());
        for (Answer answer : answers) {
            Question questionWithAnswer = questionRepository.get(answer.getQuestionId());
            if (questionWithAnswer != null) {
                questionWithAnswer.getAnswers().remove(answer);
            }
            answerRepository.delete(answer);
        }
        if (question.getImage() != null) {
            imageService.deleteImage(question.getImage());
        }
        quest.getQuestions().remove(question);
        questionRepository.delete(question);
    }


    public void update(FormData formData, Part data) throws IOException, ServletException {
        Question parsed = Mapper.question.parse(formData);
        Question question = questionRepository.get(parsed.getId());
        question.setName(parsed.getName());
        question.setText(parsed.getText());
        question.setGameState(parsed.getGameState());

        String image = S.questsDir
                + question.getQuestId() + "/"
                + question.getId()
                + Parser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
        if (isUploaded) {
            question.setImage(image);
        }
    }

}
