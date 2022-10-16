package ua.com.javarush.quest.ogarkov.service;

import com.jcabi.aspects.Loggable;
import ua.com.javarush.quest.ogarkov.dto.FormData;
import ua.com.javarush.quest.ogarkov.dto.DataTank;
import ua.com.javarush.quest.ogarkov.dto.QuestDto;
import ua.com.javarush.quest.ogarkov.dto.UserDto;
import ua.com.javarush.quest.ogarkov.entity.AbstractEntity;
import ua.com.javarush.quest.ogarkov.entity.Quest;
import ua.com.javarush.quest.ogarkov.entity.User;
import ua.com.javarush.quest.ogarkov.mapper.Mapper;
import ua.com.javarush.quest.ogarkov.repository.QuestRepository;
import ua.com.javarush.quest.ogarkov.repository.Repository;
import ua.com.javarush.quest.ogarkov.repository.UserRepository;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.util.*;

@Loggable(value = Loggable.DEBUG)
public enum PaginationService {
    INSTANCE;
    private final Repository<Quest> questRepository = QuestRepository.getInstance();
    private final Repository<User> userRepository = UserRepository.getInstance();
    private final Setting S = Setting.get();

    public DataTank getUsersPagination(FormData formData) {
        DataTank pagination = getPagination(formData);
        Collection<UserDto> users = new ArrayList<>();
        for (User user : getEntities(userRepository, formData)) {
            users.add(Mapper.user.dtoOf(user).orElseThrow());
        }
        pagination.addAttr(S.attrUsers, users);
        return pagination;
    }

    public DataTank getQuestsPagination(FormData formData) {
        DataTank pagination = getPagination(formData);
        Collection<QuestDto> quests = new ArrayList<>();
        for (Quest quest : getEntities(questRepository, formData)) {
            quests.add(Mapper.quest.dtoOf(quest).orElseThrow());
        }
        Map<Long, String> authors = new HashMap<>();
        for (QuestDto quest : quests) {
            Optional<User> optUser = Optional.ofNullable(userRepository.get(quest.getAuthorId()));
            authors.put(quest.getId(), optUser.isPresent()
                    ? optUser.get().getLogin()
                    : S.deleted);
        }
        pagination.addAttr(S.attrQuests, quests);
        pagination.addAttr(S.attrAuthors, authors);
        return pagination;
    }

    private DataTank getPagination(FormData formData) {
        DataTank pagination = DataTank.empty();
        pagination.addAttr(S.attrPageNumber, getPageNumber(formData));
        pagination.addAttr(S.attrPageSize, getPageSize(formData));
        pagination.addAttr(S.attrPageCount, getPageCount(formData));
        return pagination;
    }

    <T extends AbstractEntity> Collection<T> getEntities(Repository<T> repository, FormData formData) {
        return repository.getAll(getPageNumber(formData), getPageSize(formData));
    }

    private int getPageCount(FormData formData) {
        int questsCount = questRepository.getAll().size();
        int pages = questsCount / getPageSize(formData);
        return pages > 0
                ? ++pages
                : pages;
    }

    private int getPageSize(FormData formData) {
        String pageSizeParam = formData.getParameter(S.paramPageSize);
        return getOrDefault(pageSizeParam, S.defaultPageSize);
    }

    private int getPageNumber(FormData formData) {
        String pageNumberParam = formData.getParameter(S.paramPageNumber);
        int pageNumber = getOrDefault(pageNumberParam, S.defaultPageNumber);
        return getPageCount(formData) < pageNumber
                ? S.defaultPageNumber
                : pageNumber;
    }

    private int getOrDefault(String param, int value) {
        if (param != null && !param.isEmpty()) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignored) {
            }
        }
        return value;
    }
}
