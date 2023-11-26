package ua.com.javarush.quest.ogarkov.mapper;

import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.entity.Quest;
import ua.com.javarush.quest.ogarkov.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.dto.QuestionDto;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.List;
import java.util.Optional;

public class QuestMapper implements Mapper<Quest, QuestDto> {

    private final Setting S = Setting.get();

    @Override
    public Optional<QuestDto> dtoOf(Quest quest) {
        if (quest != null) {
            List<QuestionDto> questions = quest
                    .getQuestions()
                    .stream()
                    .map(question::dtoOf)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return Optional.of(QuestDto.with()
                    .id(quest.getId())
                    .name(quest.getName())
                    .authorId(quest.getAuthorId())
                    .text(quest.getText())
                    .image(quest.getImage())
                    .firstQuestionId(quest.getFirstQuestionId())
                    .questions(questions)
                    .build()
            );
        }
        return Optional.empty();
    }

    @Override
    public Quest parse(FormData formData) {
        long id = formData.getId();
        String name = formData.getParameter(S.inputName);
        String text = formData.getParameter(S.inputText);
        return Quest.with()
                .id(id)
                .name(name)
                .text(text)
                .build();
    }
}
