package ua.com.javarush.quest.ogarkov.questdelta.dto;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Map;

public class FormData {
    private final Map<String, String[]> parameterMap;
    private final Setting S = Setting.get();

    private FormData(HttpServletRequest request) {
        parameterMap = request.getParameterMap();
    }

    public static FormData of(HttpServletRequest request) {
        return new FormData(request);
    }

    public String getParameter(String name) {
        return parameterMap.getOrDefault(name, S.emptyStringArray)[0];
    }

    public Long getLong(String name) {
        return parseLong(getParameter(name));
    }

    public Long getId() {
        return parseLong(getParameter(S.paramId));
    }

    private long parseLong(String param) {
        long longParam = 0;
        if (param == null || param.isEmpty()) return longParam;
        try {
            longParam = Long.parseLong(param);
        } catch (NumberFormatException ignored) {
        }
        return longParam;
    }
}
