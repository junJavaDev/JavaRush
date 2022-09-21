package ua.com.javarush.quest.ogarkov.questdelta.repository;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;

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
    
//    @Override
//    public Collection<Quest> find(Quest pattern) {
//        return map.values().stream()
//                .filter(quest -> isOk(pattern, quest, Quest::getId)
//                        && isOk(pattern, quest, Quest::getName)
//                        && isOk(pattern, quest, Quest::getAuthorId)
//                        && isOk(pattern, quest, Quest::getFirstQuestion))
//                .toList();
//    }

    @Override
    public Collection<Quest> find(Quest pattern) {
        return super.find(
                pattern,
                Quest::getId,
                Quest::getName,
                Quest::getAuthorId,
                Quest::getText,
                Quest::getImage
        );
    }
}
