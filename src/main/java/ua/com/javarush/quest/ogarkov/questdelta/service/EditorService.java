package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.questdelta.dto.QuestionDto;
import ua.com.javarush.quest.ogarkov.questdelta.dto.UserDto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Objects;
import java.util.Optional;

public enum EditorService {

    INSTANCE;
    private final QuestRepository questRepository = QuestRepository.getInstance();
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final Setting S = Setting.get();

    public String getEditPath(long questId, int questionIndex) {
        Quest quest = questRepository.get(questId);
        int questionCount = quest.getQuestions().size();
        if (questionIndex >= questionCount && questionIndex > 0) {
            questionIndex--;
        }
        Question question = quest.getQuestions().get(questionIndex);
        return Go.EDIT_QUEST_CONTENT + "?"
                + S.paramId + "="
                + questId + "&"
                + S.paramQuestionId + "="
                + question.getId();
    }

    public String getEditPath(long questId) {
        return Go.EDIT_QUEST_CONTENT + "?"
                + S.paramId + "="
                + questId;
    }

    public boolean checkRights(QuestDto quest, UserDto user) {
        return (Objects.equals(user.getId(), quest.getAuthorId())
                || user.getRole() == Role.ADMIN);
    }

    public void deleteNonFirstQuestion(FormData formData) {
        long id = formData.getLong(S.paramQuestionDelete);
        Optional<QuestionDto> optQuestion = questionService.get(id);
        if (optQuestion.isPresent()) {
            QuestionDto question = optQuestion.get();
            Quest quest = questRepository
                    .get(question.getQuestId());
            Long questionId = question.getId();
            if (!Objects.equals(quest.getFirstQuestionId(), questionId)) {
                questionService.delete(questionId);
            }
        }
    }
}
