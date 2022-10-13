package ua.com.javarush.quest.ogarkov.questdelta.dto;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class DataTank {
    //Experimental
    private final Map<String, Object> attributeMap = new HashMap<>();

    public static DataTank empty() {
        return new DataTank();
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
