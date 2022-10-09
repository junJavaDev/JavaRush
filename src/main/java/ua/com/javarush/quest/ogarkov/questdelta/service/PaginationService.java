package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.dto.DataTank;
import ua.com.javarush.quest.ogarkov.questdelta.entity.AbstractEntity;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.Repository;
import ua.com.javarush.quest.ogarkov.questdelta.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PaginationService {
    INSTANCE;
    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final Setting S = Setting.get();


    public DataTank getQuestsPagination(DataTank formData) {
        DataTank pagination = getPagination(formData);
        Collection<Quest> quests = getEntities(questRepository, formData);
        Map<Long, String> authors = new HashMap<>();
        for (Quest quest : quests) {
            Optional<User> optUser = userRepository.get(quest.getAuthorId());
            authors.put(quest.getId(), optUser.isPresent()
                    ? optUser.get().getLogin()
                    : S.deleted);
        }
        pagination.addAttribute(S.attrQuests, quests);
        pagination.addAttribute(S.attrAuthors, authors);
        return pagination;
    }

    private DataTank getPagination(DataTank formData) {
        DataTank pagination = DataTank.empty();
        pagination.addAttribute(S.attrPageNumber, getPageNumber(formData));
        pagination.addAttribute(S.attrPageSize, getPageSize(formData));
        pagination.addAttribute(S.attrPageCount, getPageCount(formData));
        return pagination;
    }

    <T extends AbstractEntity> Collection<T> getEntities(Repository<T> repository, DataTank formData) {
        return repository.getAll(getPageNumber(formData), getPageSize(formData));
    }

    public int getPageCount(DataTank formData) {
        int questsCount = questRepository.getAll().size();
        int pages = questsCount / getPageSize(formData);
        return pages > 0
                ? ++pages
                : pages;
    }

    public int getPageSize(DataTank formData) {
        String pageSizeParam = formData.getParameter(S.paramPageNumber);
        return getOrDefault(pageSizeParam, S.defaultPageSize);
    }

    public int getPageNumber(DataTank formData) {
        String pageNumberParam = formData.getParameter(S.paramPageNumber);
        int pageNumber = getOrDefault(pageNumberParam, S.defaultPageNumber);
        return getPageCount(formData) < pageNumber
                ? S.defaultPageNumber
                : pageNumber;
    }

    private int getOrDefault(String param, int value) {
        if (param != null && param.isEmpty()) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }
}
