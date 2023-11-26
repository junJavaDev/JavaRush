package ua.com.javarush.quest.ogarkov.mapper;

import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.entity.GameState;
import ua.com.javarush.quest.ogarkov.dto.AnswerDto;
import ua.com.javarush.quest.ogarkov.dto.QuestionDto;
import ua.com.javarush.quest.ogarkov.entity.Question;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.List;
import java.util.Optional;

public class QuestionMapper implements Mapper<Question, QuestionDto> {

    private final Setting S = Setting.get();

    @Override
    public Optional<QuestionDto> dtoOf(Question question) {
        if (question != null) {
            List<AnswerDto> answers = question
                    .getAnswers()
                    .stream()
                    .map(answer::dtoOf)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .toList();
            return Optional.of(QuestionDto.with()
                    .id(question.getId())
                    .questId(question.getQuestId())
                    .gameState(question.getGameState())
                    .name(question.getName())
                    .text(question.getText())
                    .image(question.getImage())
                    .answers(answers)
                    .build()
            );
        }
        return Optional.empty();
    }

    @Override
    public Question parse(FormData formData) {
        long id = formData.getLong(S.paramQuestionId);
        Long questId = formData.getId();
        String name = formData.getParameter(S.inputName);
        String text = formData.getParameter(S.inputText);
        GameState gameState = GameState.valueOf(formData.getParameter(S.inputGameState));
        return Question.with()
                .id(id)
                .questId(questId)
                .name(name)
                .text(text)
                .gameState(gameState)
                .build();
    }
}
