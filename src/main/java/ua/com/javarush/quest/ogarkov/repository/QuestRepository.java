package ua.com.javarush.quest.ogarkov.repository;

import ua.com.javarush.quest.ogarkov.entity.Quest;

import java.util.Collection;

public class QuestRepository extends AbstractRepository<Quest> {

    private static class QuestRepositoryHolder {
        public static final QuestRepository HOLDER_INSTANCE = new QuestRepository();
    }

    public static QuestRepository getInstance() {
        return QuestRepository.QuestRepositoryHolder.HOLDER_INSTANCE;
    }

    private QuestRepository() {
    }

    @Override
    public Collection<Quest> find(Quest pattern) {
        return super.find(
                pattern,
                Quest::getId,
                Quest::getName,
                Quest::getAuthorId,
                Quest::getText,
                Quest::getFirstQuestionId
        );
    }
}
