package ua.com.javarush.quest.ogarkov.questdelta.service;

import ua.com.javarush.quest.ogarkov.questdelta.dto.Dto;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.*;

public enum PaginationService {
    INSTANCE;
    private final QuestService questService = QuestService.INSTANCE;
    private final Setting S = Setting.get();

    public int getPageCount(Dto dto) {
        int questsCount = questService.getAll().size();
        int pages = questsCount / getPageSize(dto);
        return pages > 0
                ? ++pages
                : pages;
    }

    public int getPageSize(Dto dto) {
        String pageSizeParam = dto.getParameter(S.paramPageNumber);
        return getOrDefault(pageSizeParam, S.defaultPageSize);
    }

    public int getPageNumber(Dto dto) {
        String pageNumberParam = dto.getParameter(S.paramPageNumber);
        int pageNumber = getOrDefault(pageNumberParam, S.defaultPageNumber);
        return getPageCount(dto) < pageNumber
                ? S.defaultPageNumber
                : pageNumber;
    }

    private int getOrDefault(String param, int value) {
        if (param != null && param.isEmpty()) {
            try {
                value = Integer.parseInt(param);
            } catch (NumberFormatException ignored) {}
        }
        return value;
    }
}
