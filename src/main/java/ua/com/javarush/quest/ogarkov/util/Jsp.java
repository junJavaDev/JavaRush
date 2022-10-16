package ua.com.javarush.quest.ogarkov.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.ogarkov.settings.Setting;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@UtilityClass
public class Jsp {
    private final Setting S = Setting.get();

    public static void forward(HttpServletRequest request, HttpServletResponse response, String uriString) throws ServletException, IOException {
        String path = S.pagesDir + "%s.jsp".formatted(uriString);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String uri) throws IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }

    public static String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile(".*(/[a-z-]*)").matcher(uri.substring(uri.lastIndexOf("/")));
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new UnsupportedOperationException(S.incorrectUri + uri);
        }
    }
}
