package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.Answer;

import java.util.Collection;

public class AnswerRepository extends AbstractRepository<Answer> {

    private AnswerRepository() {
    }

    public static AnswerRepository getInstance() {
        return AnswerRepositoryHolder.HOLDER_INSTANCE;
    }

    @Override

    public Collection<Answer> find(Answer pattern) {
        return super.find(
                pattern,
                Answer::getId,
                Answer::getQuestionId,
                Answer::getNextQuestionId,
                Answer::getText
        );
    }

    private static class AnswerRepositoryHolder {
        public static final AnswerRepository HOLDER_INSTANCE = new AnswerRepository();
    }
}
