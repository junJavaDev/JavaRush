package ua.com.javarush.quest.ogarkov.questdelta.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.ogarkov.questdelta.entity.GameSession;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Quest;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Optional;

@UtilityClass
public class ReqParser {

    private final Setting S = Setting.get();

    public String getFileExtension(String fileName) {
        String extension = "";
        if (fileName.contains(".")) {
            extension = fileName.substring(fileName.lastIndexOf("."));
        }
        return extension;
    }

    public String getCommand (HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        int lastSeparatorIndex = requestURI.lastIndexOf('/');
        return lastSeparatorIndex >= 0
                ? requestURI.substring(lastSeparatorIndex)
                : requestURI;
    }

    public Long getLong(HttpServletRequest req, String key) {
        String inputId = req.getParameter(key);
        return parseLong(inputId);
    }

    public Long getId(HttpServletRequest req) {
        String inputId = req.getParameter(S.paramId);
        return parseLong(inputId);
    }

    public Optional<User> getUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        return getOptional(User.class, user);
    }


    private <T> Optional<T> getOptional(Class<T> clazz, Object o) {
        return o != null
                ? Optional.of(clazz.cast(o))
                : Optional.empty();
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
