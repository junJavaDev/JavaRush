package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.entity.Quest;
import ua.com.javarush.quest.ogarkov.entity.Question;
import ua.com.javarush.quest.ogarkov.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.entity.User;
import ua.com.javarush.quest.ogarkov.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.repository.Repository;
import ua.com.javarush.quest.ogarkov.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.settings.Setting;
import ua.com.javarush.quest.ogarkov.util.Parser;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Loggable(value = Loggable.DEBUG)
public enum QuestService {

    INSTANCE;

    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final ImageService imageService = ImageService.INSTANCE;
    private final ParseContentService parseContentService = ParseContentService.INSTANCE;
    private final Setting S = Setting.get();

    public Optional<QuestDto> get(long id) {
        return Mapper.quest.dtoOf(questRepository.get(id));
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public QuestDto create(FormData formData, Part imageData, Part twineData, long authorId) throws ServletException, IOException {
        Quest quest = createEmptyQuest(authorId);
        fillQuest(quest, formData, imageData, twineData);
        return Mapper.quest.dtoOf(quest).orElseThrow();
    }

    public void update(FormData formData, Part imageData, Part twineData) throws ServletException, IOException {
        Quest quest = questRepository.get(formData.getId());
        fillQuest(quest, formData, imageData, twineData);
    }

    private void fillQuest(Quest quest, FormData formData, Part imageData, Part twineData) throws IOException {
        Quest parsed = Mapper.quest.parse(formData);
        quest.setName(parsed.getName());
        quest.setText(parsed.getText());
        updateImage(quest, imageData);
        String content = formData.getParameter(S.inputContent);
        parseContentService.parseContent(quest, content);
        parseContentService.parseTwine(quest, twineData);
    }

    public void delete(FormData formData) {
        long id = formData.getLong(S.paramQuestDelete);
        Quest quest = questRepository.get(id);
        if (quest != null) {
            List<Question> questions = quest.getQuestions();
            for (int i = questions.size() - 1; i >= 0; i--) {
                Question question = questions.get(i);
                questionService.delete(question.getId());
            }
            for (Question question : questions) {
                questionService.delete(question.getId());
            }
            imageService.deleteImage(quest.getImage());
            User author = userRepository.get(quest.getAuthorId());
            if (author != null) {
                author.getQuests().remove(quest);
            }
            questRepository.delete(quest);
        }
    }

    private Quest createEmptyQuest(long authorId) {
        Quest quest = Quest.with().authorId(authorId).build();
        User user = userRepository.get(authorId);
        user.getQuests().add(quest);
        questRepository.create(quest);
        Question firstQuestion = questionService.createEmpty(quest.getId());
        quest.setFirstQuestionId(firstQuestion.getId());
        return quest;
    }

    private void updateImage(Quest quest, Part data) throws IOException {
        String image = S.questsDir
                + quest.getId()
                + S.questImgPrefix
                + Parser.getFileExtension(data.getSubmittedFileName());
        boolean isUploaded = imageService.uploadImage(image, data.getInputStream());
        if (isUploaded) {
            quest.setImage(image);
        }
    }


}
