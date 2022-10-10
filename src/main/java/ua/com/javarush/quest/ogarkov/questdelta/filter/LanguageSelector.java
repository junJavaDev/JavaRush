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
import ua.com.javarush.quest.ogarkov.questdelta.settings.Go;
import ua.com.javarush.quest.ogarkov.questdelta.settings.Setting;

import java.util.Optional;

@WebFilter(Go.ALL)
public class LanguageSelector implements Filter {

    private final Setting S = Setting.get();

    @Override
    @SneakyThrows
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession httpSession = request.getSession();
        Optional<String> localeParam = Optional.ofNullable(request.getParameter(S.paramLang));
        Optional<Object> sessionUser = Optional.ofNullable(httpSession.getAttribute(S.attrUser));
        Optional<Object> sessionLocale = Optional.ofNullable(httpSession.getAttribute(S.attrLang));

        if (localeParam.isPresent()) {
            Language chosenLanguage = Language.valueOf(localeParam.get());
            sessionUser.ifPresent(opUser -> {
                User user = (User) opUser;
                user.setLanguage(chosenLanguage);
            });

            httpSession.setAttribute(S.attrLang, chosenLanguage.name());
        } else if (sessionLocale.isEmpty()) {
            httpSession.setAttribute(S.attrLang, sessionUser
                    .map(user -> ((User) user)
                            .getLanguage()
                            .name())
                    .orElse(S.defaultLanguage));
        }
        chain.doFilter(request, response);
    }
}
