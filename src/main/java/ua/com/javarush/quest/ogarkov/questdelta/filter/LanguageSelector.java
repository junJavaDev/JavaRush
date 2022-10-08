package ua.com.javarush.quest.ogarkov.questdelta.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.SneakyThrows;
import ua.com.javarush.quest.ogarkov.questdelta.entity.Language;
import ua.com.javarush.quest.ogarkov.questdelta.entity.User;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Optional;

@WebFilter(value = "/*")
public class LanguageSelector implements Filter {

    private final Setting S = Setting.get();

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Optional<Object> sessionUser = Optional.ofNullable(httpSession.getAttribute("user"));
        Optional<String> localeParam = Optional.ofNullable(request.getParameter("lang"));
        Optional<Object> sessionLocale = Optional.ofNullable(httpSession.getAttribute("lang"));

        if (localeParam.isPresent()) {
            Language chosenLanguage = Language.valueOf(localeParam.get());
            sessionUser.ifPresent(opUser -> {
                User user = (User) opUser;
                user.setLanguage(chosenLanguage);
            });

            httpSession.setAttribute("lang", chosenLanguage.name());
        } else if (sessionLocale.isEmpty()) {
            httpSession.setAttribute("lang", sessionUser
                    .map(user -> ((User) user)
                            .getLanguage()
                            .name())
                    .orElse(S.defaultLanguage));
        }
        chain.doFilter(request, response);
    }
}
