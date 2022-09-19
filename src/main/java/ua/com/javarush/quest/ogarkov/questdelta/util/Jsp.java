package ua.com.javarush.quest.ogarkov.questdelta.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Jsp {
    public static void forward(HttpServletRequest request, HttpServletResponse response, String uriString) throws ServletException, IOException {
        String path = "WEB-INF/pages%s.jsp".formatted(uriString);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public static void redirect(HttpServletResponse response, String uri) throws IOException {
        response.sendRedirect(uri);
    }

    public static String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile("/[a-z]*").matcher(uri);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new UnsupportedOperationException("incorrect uri" + uri);
        }
    }
}
