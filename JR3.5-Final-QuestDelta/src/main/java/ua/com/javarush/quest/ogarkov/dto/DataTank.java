package ua.com.javarush.quest.ogarkov.dto;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Iterator;
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

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("FormData{");
        Iterator<Map.Entry<String, Object>> iterator = attributeMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            stringBuilder.append(String.format("%s=%s", entry.getKey(), entry.getValue()));
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
