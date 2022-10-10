package ua.com.javarush.quest.ogarkov.questdelta.dto;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.HashMap;
import java.util.Map;

public class DataTank {

    private final Setting S = Setting.get();
    private final Map<String, String[]> parameterMap;
    private final Map<String, Object> attributeMap = new HashMap<>();

    private DataTank(HttpServletRequest request) {
        parameterMap = request.getParameterMap();
    }

    private DataTank() {
        parameterMap = new HashMap<>();
    }

    public static DataTank of(HttpServletRequest request) {
        return new DataTank(request);
    }

    public static DataTank empty() {
        return new DataTank();
    }

    public String getParameter(String name) {
        return parameterMap.getOrDefault(name, S.emptyStringArray)[0];
    }

    public void addAttr(String name, Object value) {
        attributeMap.put(name, value);
    }

    public void fillRequest(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : attributeMap.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
    }
}
