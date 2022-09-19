package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Question;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestionRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum QuestionService {

    INSTANCE;

    private final Repository<Question> questionRepository = QuestionRepository.getInstance();

    public Collection<Question> find(Question pattern) {
        return questionRepository.find(pattern);
    }

    public Optional<Question> get(long id) {
        return questionRepository.get(id);
    }

    public Collection<Question> getAll() {
        return questionRepository.getAll();
    }

    public void create(Question question) {
        questionRepository.create(question);
    }

    public void update(Question question) {
        questionRepository.update(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }


}
