package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

public enum QuestService {

    INSTANCE;

    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final ImageService imageService = ImageService.INSTANCE;

    public Collection<Quest> find(Quest pattern) {
        return questRepository.find(pattern);
    }

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Collection<Quest> getAll(int pageNumber, int pageSize) {
        return questRepository.getAll(pageNumber, pageSize);
    }

    public Quest create(HttpServletRequest request) throws ServletException, IOException {
        User user = ReqParser.getUser(request).orElseThrow();
        String name = request.getParameter("name");
        String text = request.getParameter("text");
        String pattern = request.getParameter("content");
        Part imagePart = request.getPart("image");

        Question firstQuestion = Question.with().gameState(GameState.PLAY).build();
        questionRepository.create(firstQuestion);

        Quest quest = Quest.with()
                .authorId(user.getId())
                .firstQuestionId(firstQuestion.getId())
                .name(name)
                .text(text)
                .build();

        quest.getQuestions().add(firstQuestion);
        create(quest);
        user.getQuests().add(quest);

        Long questId = quest.getId();
        String image = "quests/" + questId + "/quest_image" + ReqParser.getFileExtension(imagePart.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(image, imagePart.getInputStream());
        if (isUploaded) {
            quest.setImage(image);
        }

        return quest;

    }


    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }


}
