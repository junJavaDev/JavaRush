package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.Question;

import java.util.Collection;

public class QuestionRepository extends AbstractRepository<Question> {

    private static class QuestionRepositoryHolder {
        public static final QuestionRepository HOLDER_INSTANCE = new QuestionRepository();
    }

    public static QuestionRepository getInstance() {
        return QuestionRepository.QuestionRepositoryHolder.HOLDER_INSTANCE;
    }

    private QuestionRepository() {
    }

    @Override
    public Collection<Question> find(Question pattern) {
        return super.find(
                pattern,
                Question::getId,
                Question::getQuestId,
                Question::getGameState,
                Question::getName,
                Question::getText,
                Question::getImage
        );
    }
}
