package ua.com.javarush.quest.ogarkov.questdelta.dto;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Dto {

    private final Setting S = Setting.get();

    private final Map<String, String[]> parameterMap;

    private Dto(HttpServletRequest request) {
        parameterMap = request.getParameterMap();
    }

    public static Dto of(HttpServletRequest request) {
        return new Dto(request);
    }

    public String getParameter(String name) {
        return parameterMap.getOrDefault(name, S.emptyStringArray)[0];
    }

    public Map<String, String[]> getParameters() {
        return parameterMap;
    }

    public Long getId() {
        return parameterMap.containsKey("id")
                ? Long.valueOf(getParameter("id"))
                : null;
    }

    @Override
    public String toString() {
        return parameterMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + Arrays.toString(e.getValue()))
                .collect(Collectors.joining());
    }

}
