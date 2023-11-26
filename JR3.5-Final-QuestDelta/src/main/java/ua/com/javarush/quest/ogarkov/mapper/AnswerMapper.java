package ua.com.javarush.quest.ogarkov.mapper;

import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.AnswerDto;
import ua.com.javarush.quest.ogarkov.entity.Answer;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.Optional;

public class AnswerMapper implements Mapper<Answer, AnswerDto> {

    private final Setting S = Setting.get();

    @Override
    public Optional<AnswerDto> dtoOf(Answer answer) {
        return answer != null
                ? Optional.of(AnswerDto.with()
                .id(answer.getId())
                .questionId(answer.getQuestionId())
                .nextQuestionId(answer.getNextQuestionId())
                .text(answer.getText())
                .build())
                : Optional.empty();
    }

    @Override
    public Answer parse(FormData formData) {
        long questionId = formData.getLong(S.paramQuestionId);
        long nextQuestionId = formData.getLong(S.inputNextQuestionId);
        String text = formData.getParameter(S.inputAnswer);
        return Answer.with()
                .questionId(questionId)
                .nextQuestionId(nextQuestionId)
                .text(text)
                .build();
    }
}
