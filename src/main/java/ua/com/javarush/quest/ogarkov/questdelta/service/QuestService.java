package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;

import java.util.Collection;
import java.util.Optional;

public enum QuestService {

    INSTANCE;

    private final Repository<Quest> questRepository = QuestRepository.getInstance();

    public Collection<Quest> find(Quest pattern) {
        return questRepository.find(pattern);
    }

    public Optional<Quest> get(long id) {
        return questRepository.get(id);
    }

    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    public void create(Quest quest) {
        questRepository.create(quest);
    }

    public void update(Quest quest) {
        questRepository.update(quest);
    }

    public void delete(Quest quest) {
        questRepository.delete(quest);
    }


}
