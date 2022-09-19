package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Answer;
import ua.com.javarush.quest.ogarkov.questdelta.repository.AnswerRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum AnswerService {

    INSTANCE;

    private final Repository<Answer> answerRepository = new AnswerRepository();

    public Collection<Answer> find(Answer pattern) {
        return answerRepository.find(pattern);
    }

    public Optional<Answer> get(long id) {
        return answerRepository.get(id);
    }

    public Collection<Answer> getAll() {
        return answerRepository.getAll();
    }

    public void create(Answer answer) {
        answerRepository.create(answer);
    }

    public void update(Answer answer) {
        answerRepository.update(answer);
    }

    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }


}
