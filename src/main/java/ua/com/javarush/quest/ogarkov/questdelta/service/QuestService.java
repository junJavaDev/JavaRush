package ua.com.javarush.quest.ogarkov.questdelta.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;
import ua.com.javarush.quest.ogarkov.questdelta.util.ReqParser;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public enum QuestService {

    INSTANCE;


    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;
    private final ParseContentService parseContentService = ParseContentService.INSTANCE;
    private final Setting S = Setting.get();

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public Quest create(HttpServletRequest req) throws ServletException, IOException {
        Quest emptyQuest = createEmptyQuest(req);
        Quest filledQuest = fillQuest(req, emptyQuest);
        update(filledQuest);
        String content = req.getParameter(S.inputContent);
        parseContentService.parseContent(content, filledQuest);
        parseContentService.parseTwine(req, filledQuest);
        return filledQuest;
    }

    public void update(HttpServletRequest req) throws ServletException, IOException {
        long id = ReqParser.getLong(req, S.paramId);
        Optional<Quest> optQuest = get(id);
        if (optQuest.isPresent()) {
            Quest filledQuest = fillQuest(req, optQuest.get());
            update(filledQuest);
            String content = req.getParameter(S.inputContent);
            parseContentService.parseContent(content, filledQuest);
        }
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(HttpServletRequest req) {
        long id = ReqParser.getLong(req, S.paramQuestDelete);
        Optional<Quest> optQuest = get(id);
        if (optQuest.isPresent()) {
            Quest quest = optQuest.get();
            for (Question question : quest.getQuestions()) {
                questionRepository.delete(question);
            }
            imageService.deleteImage(quest.getImage());
            Optional<User> optAuthor = userRepository.get(quest.getAuthorId());
            optAuthor.ifPresent(author -> author.getQuests().remove(quest));
            delete(quest);
        }
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }

    private Quest createEmptyQuest(HttpServletRequest request) {
        User author = ReqParser.getUser(request).orElseThrow();
        Quest quest = Quest.with().authorId(author.getId()).build();
        create(quest);
        Question firstQuestion = questionService.createEmpty(quest);
        quest.setFirstQuestionId(firstQuestion.getId());
        return quest;
    }

    private Quest fillQuest(HttpServletRequest request, Quest existQuest) throws ServletException, IOException {
        long id = existQuest.getId();
        Long authorId = existQuest.getAuthorId();
        List<Question> questions = existQuest.getQuestions();
        long firstQuestionId = existQuest.getFirstQuestionId();
        String image = existQuest.getImage();
        String name = request.getParameter(S.inputName);
        String text = request.getParameter(S.inputText);
        Part part = request.getPart(S.inputImage);
        Quest quest = Quest.with().id(id).firstQuestionId(firstQuestionId).authorId(authorId).questions(questions).image(image).name(name).text(text).build();

        String newImage = S.questsDir + id + S.questImgPrefix + ReqParser.getFileExtension(part.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(newImage, part.getInputStream());
        if (isUploaded) {
            quest.setImage(newImage);
        }
        return quest;
    }


}
