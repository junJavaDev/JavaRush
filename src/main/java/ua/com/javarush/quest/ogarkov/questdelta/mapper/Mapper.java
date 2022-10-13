package ua.com.javarush.quest.ogarkov.questdelta.mapper;

import ua.com.javarush.quest.ogarkov.questdelta.dto.*;
import ua.com.javarush.quest.ogarkov.questdelta.entity.*;

import java.util.Optional;

public interface Mapper<E extends AbstractEntity, R> {

    Mapper<User, UserDto> user = new UserMapper();
    Mapper<Quest, QuestDto> quest = new QuestMapper();
    Mapper<Question, QuestionDto> question = new QuestionMapper();
    Mapper<Answer, AnswerDto> answer = new AnswerMapper();
    Mapper<Game, GameDto> game = new GameMapper();

    Optional<R> dtoOf(E entity);

    E parse(FormData formData);
}
