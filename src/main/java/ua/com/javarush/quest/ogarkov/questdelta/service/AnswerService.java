package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.dto.AnswerDto;
import ua.com.javarush.quest.ogarkov.questdelta.dto.FormData;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Collection;
import java.util.Optional;

public enum AnswerService {

    INSTANCE;
    private final Repository<Answer> answerRepository = AnswerRepository.getInstance();
    private final Repository<Question> questionRepository = QuestionRepository.getInstance();
    private final Setting S = Setting.get();

    public Optional<AnswerDto> get(long id) {
        Answer answer = answerRepository.get(id);
        return Mapper.answer.dtoOf(answer);
    }

    public Collection<AnswerDto> getAll() {
        return answerRepository.getAll()
                .stream()
                .map(Mapper.answer::dtoOf)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public void create(FormData formData) {
        Answer answer = Mapper.answer.parse(formData);
        Question question = questionRepository.get(answer.getQuestionId());
        answerRepository.create(answer);
        question.getAnswers().add(answer);
    }

    public void delete(FormData formData) {
        Answer answer = answerRepository.get(formData.getLong(S.paramAnswerDelete));
        Question question = questionRepository.get(answer.getQuestionId());
        question.getAnswers().remove(answer);
        answerRepository.delete(answer);
    }
}
