package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Role;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.List;
import java.util.Objects;

public enum EditorService {

    INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final Setting S = Setting.get();


    public String getEditPath(long questId, long questionIndex) {
        Quest quest = questService.get(questId).orElseThrow();
        int questionCount = quest.getQuestions().size();
        if (questionIndex >= questionCount && questionIndex > 0) {
            questionIndex--;
        }
        return Go.EDIT_QUEST_CONTENT + "?"
                + S.paramId + "="
                + questId + "&"
                + S.paramQuestionIndex + "="
                + questionIndex;
    }

    public String getEditPath(long questId) {
        return Go.EDIT_QUEST_CONTENT + "?"
                + S.paramId + "="
                + questId;
    }

    public boolean checkRights(Quest quest, User user) {
        return  (Objects.equals(user.getId(), quest.getAuthorId())
                || user.getRole() == Role.ADMIN);
    }

    public void deleteNonFirstQuestion(Question question) {
        Quest quest = questService
                .get(question.getQuestId())
                .orElseThrow();
        if (!Objects.equals(quest.getFirstQuestionId(), question.getId())) {
            quest.getQuestions().remove(question);
            questionService.delete(question);
        }
    }
}
