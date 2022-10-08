package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Objects;

public enum EditorService {

    INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final QuestionService questionService = QuestionService.INSTANCE;
    private final Setting S = Setting.get();


    public String getEditPath(Question question) {
        long questId = question.getQuestId();
        Quest quest = questService.get(questId).orElseThrow();
        int questionIndex = quest.getQuestions().indexOf(question);
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

    public void deleteNonFirstQuestion(Question question) {
        Quest quest = questService
                .get(question.getQuestId())
                .orElseThrow();
        if (!Objects.equals(quest.getFirstQuestionId(), question.getId())) {
            questionService.delete(question);
        }
    }
}
