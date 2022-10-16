package ua.com.javarush.quest.ogarkov.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.ogarkov.settings.Setting;

@UtilityClass
public class Parser {

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

    public long userId(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return session.getAttribute(S.attrUserId) != null
                ? (Long) session.getAttribute(S.attrUserId)
                : 0L;
    }
}
